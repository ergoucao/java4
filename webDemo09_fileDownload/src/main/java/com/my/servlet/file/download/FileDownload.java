package com.my.servlet.file.download;

import com.my.filter.FilterServlet;
import com.my.pojo.Student;
import com.my.service.imp.FileServiceImpl;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import org.apache.tools.zip.*;

@WebServlet(name = "FileDownload")
public class FileDownload extends HttpServlet
{
//   声明日志对象
    Logger logger=Logger.getLogger(FileDownload.class);
    final int FILEN=15;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        获取文件名；
        String wid = req.getParameter("wid");
//        for (int i=0;(data=req.getParameter(i+""))!=null;i++)
//        {
//            String error=null;
//            JSONArray json=new JSONArray(data);
//
//            for (int i=0;i<json.length();i++)
//            {
//                JSONObject js=json.getJSONObject(i);
//                download(String.valueOf(js.get("wid")),resp);
//            }
//            String wid=data;
//            logger.debug(wid);
//            download(String.valueOf(wid),resp);
//        }
        if (wid != null)
        {
//            单个文件下载
            download(req,resp);
        }
        else
        {
//            多个文件打包为一个zip批量下载
            batchDonload(req,resp);
        }
    }

//   浏览器弹窗下载。
    private void download(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
//        logger.debug(wid);
//        通过wid获取地址；
        String wid=req.getParameter("wid");
        Student s = new FileServiceImpl().getFilePath(wid);
        logger.debug(s);
        String path = s.getFilePath();
        logger.debug("执行下载获得path");
        String fileName = s.getFileName();
        fileName = path.substring(path.lastIndexOf("\\") + 1);
        logger.debug("执行下载获得fileName" + fileName);
//        创建File对象
        File file = new File(path);
//      判断文件是否存在
        if (!file.exists())
        {
            logger.debug("文件已经不存在");
            try
            {
                resp.getWriter().write("文件已经不存在");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            logger.debug("执行下载进入下载流");
//            设置下载文件响应头
            resp.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
//            读取下载的文件，保存到文件输入流。
            FileInputStream in = null;
            try
            {
                in = new FileInputStream(path);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
//            创建输出流
            OutputStream out = resp.getOutputStream();
//            创建缓冲区
            byte buffer[] = new byte[1024];
            int length = 0;
//            将输入流读入缓冲区
            logger.debug("进入缓冲区下载流");
            while ((length = in.read(buffer)) != -1)
            {
//                输出缓冲区的的内容到浏览器，实现文件下载
                out.write(buffer, 0, length);
            }
//            关闭文件输入流
            in.close();
//            关闭文件输出流
            out.close();
            logger.debug("下载完成");
        }
    }

    // 批量下载
    private void batchDonload(HttpServletRequest req, HttpServletResponse resp)
    {
        String fileName = "downloadFile";
        String[] memName = new String[FILEN];
        String[] filePath = new String[FILEN];
        String wid = null;
        int i = 0;

    //    获取下载文件的路径和文件名称。
        for (i = 0; i < FILEN && (wid = req.getParameter(i + "")) != null; i++)
        {
            Student s = new FileServiceImpl().getFilePath(wid);
            logger.debug(s);
            filePath[i] = s.getFilePath();
            logger.debug("执行下载获得path"+filePath[i]);
            memName[i] = s.getFileName();
            memName[i] = filePath[i].substring(filePath[i].lastIndexOf("\\") + 1);
        }

        byte[] buffer = new byte[1024];
        Date date = new Date();
    //    临时文件的位置。
        String tempPath = "D:\\aa\\ff\\temp.zip";
        File file = new File("D:\\aa\\ff");
        if (!file.isDirectory() && !file.exists())
        {
            file.mkdirs();
        }
        try
        {
    //        设置流和格式
            logger.debug(tempPath);
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(tempPath));
            out.setEncoding("utf-8");
    //        文件打包
            for (int j = 0; j < i; j++)
            {
                File f = new File(filePath[j]);
                FileInputStream fis = new FileInputStream(f);
                out.putNextEntry(new ZipEntry(memName[j]));
                int length;
                while ((length = fis.read(buffer)) > 0)
                {
                    out.write(buffer, 0, length);
                }
                out.closeEntry();
                fis.close();
            }
            out.close();
            download(req,resp,fileName+".zip",tempPath);
            File temp=new File(tempPath);
            if (temp.exists())
            {
                temp.delete();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
//    弹窗重载
    private void download(HttpServletRequest req, HttpServletResponse resp,String fileName,String path) throws IOException
    {
        logger.debug("执行下载获得fileName" + fileName);
//        创建File对象
        File file = new File(path);
//      判断文件是否存在
        if (!file.exists())
        {
            logger.debug("文件已经不存在");
            try
            {
                resp.getWriter().write("文件已经不存在");
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            logger.debug("执行下载进入下载流");
//            设置下载文件响应头
            resp.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
//            读取下载的文件，保存到文件输入流。
            FileInputStream in = null;
            try
            {
                in = new FileInputStream(path);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
//            创建输出流
            OutputStream out = resp.getOutputStream();
//            创建缓冲区
            byte buffer[] = new byte[1024];
            int length = 0;
//            将输入流读入缓冲区
            logger.debug("进入缓冲区下载流");
            while ((length = in.read(buffer)) != -1)
            {
//                输出缓冲区的的内容到浏览器，实现文件下载
                out.write(buffer, 0, length);
            }
//            关闭文件输入流
            in.close();
//            关闭文件输出流
            out.close();
            logger.debug("下载完成");
        }
    }
}
