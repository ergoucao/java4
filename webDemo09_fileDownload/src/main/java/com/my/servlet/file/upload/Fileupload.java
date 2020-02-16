package com.my.servlet.file.upload;

import com.my.pojo.Student;
import com.my.service.FileService;
import com.my.service.imp.FileServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class Fileupload extends HttpServlet
{

    Logger logger=Logger.getLogger(Fileupload.class);
    Student s=new Student();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        logger.debug("开始使用fileUplaod0");
//        设置请求编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        logger.debug("开始使用fileUplaod");
//      获得上传文件保存目录
        String fileUpload=this.getServletContext().getRealPath("/WEB-INF/upload");
        File file=new File(fileUpload);
        logger.debug("获得保存目录"+fileUpload);

//        如果目录不存在则创建目录
        if (!file.exists() && !file.isDirectory()) {
            logger.debug(fileUpload+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
//        消息提示
        String details=null;
        String error=null;

//      使用Apache组件处理文件上传
//      创建DiskFileItemFactory；
        DiskFileItemFactory dffactory=new DiskFileItemFactory();
//      创建ServletFileUpload对象
        ServletFileUpload sfupload=new ServletFileUpload(dffactory);
        try
        {

            List<FileItem> list=sfupload.parseRequest(req);
            for (FileItem i : list)
            {
//                fileitem是普通输入数据
                if (i.isFormField())
                {
                    String name=i.getFieldName();
                    String value=i.getString("utf-8");
                    savefile(name,value);
                    logger.debug(name+value);
                }
                else
                {//不是
                    String fileName=i.getName();
                    logger.debug(fileName);
                    //如果filename为有效名
                    if (!(fileName==null||fileName.trim().equals("")))
                    {
//                        获取上传文件的文件名部分。
                        fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                        InputStream in=i.getInputStream();
                        logger.debug("指定路径："+fileUpload+"\\"+fileName);
                        FileOutputStream out=new FileOutputStream(fileUpload+"\\"+fileName);
                        Student s=savefile("filePath",fileUpload+"\\"+fileName);
//                        存储文件
                        byte buffer[]=new byte[1024];

                        int length=0;
                        while ((length=in.read(buffer))>0)
                        {
                            out.write(buffer,0,length);
                        }
                        in.close();
                        out.close();
//                        删除临时文件
                        i.delete();
                        details=fileName+"文件上传成功";
                        error="0";
                        logger.debug("文件上传");
                    }
                }
            }
        } catch (FileUploadException e)
        {
            details="文件上传失败";
            logger.debug("文件上传");
            error="1";
            e.printStackTrace();
        }
        logger.debug(s);
        req.setAttribute("details",details);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error",error);
        jsonObject.put("details",details);
        logger.debug(jsonObject);
        resp.getWriter().write(String.valueOf(jsonObject));
//        req.getRequestDispatcher("/file/details.jsp").forward(req,resp);
    }

    Student savefile(String name,String value)
    {

        if (name.equals("sId"))
            s.setStudentId(value);
        else if (name.equals("sname"))
            s.setStudentName(value);
        else if (name.equals("checkCode"))
            s.setTest(value);
        else if (name.equals("filePath"))
        {
            s.setFilePath(value);
            s.setsId("DEFAULT");
            s.setWid("DEFAULT");
//      调用service层方法
            logger.debug("调用了service层方法");
            FileService fs=new FileServiceImpl();
            s=fs.saveFile(s);
            return s;
        }
        return null;
    }
}