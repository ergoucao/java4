package com.my.servlet;

import com.my.pojo.Notice;
import com.my.pojo.Student;
import com.my.pojo.User;
import com.my.service.GiveTestNoticeService;
import com.my.service.imp.FileServiceImpl;
import com.my.service.imp.ManagerServiceImpl;
import org.apache.commons.io.output.ClosedOutputStream;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/*
    功能：管理员后台一系列的控制。
    author:cx
*/
public class ManagerServlet extends HttpServlet
{
    Logger logger=Logger.getLogger(ManagerServlet.class);
    final int NUMS=10;
    @Override
/*
     参数：HttpServletRequest,HttpServletResponse。
     功能：后台控制的service方法。
     返回值：void
*/
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        设置请求编码格式
        req.setCharacterEncoding("utf-8");
//        设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        logger.debug("开始执行ManagerServlet service方法");
//        声明Notice对象
        Notice notice=new Notice();
//        获取请求数据
        String oper=req.getParameter("oper");
        String test1=req.getParameter("test");
        logger.debug("获得oper:"+oper);
        logger.debug("获得test:"+test1);

        if (oper.equals("giveTestNotice"))//发布考核方法。
        {
            logger.debug("调用giveTestNotice()方法");
//            调用giveTestNotice()方法
            notice=giveTestNotice(req,resp);
            JSONObject jsonObject=new JSONObject();
            if (notice==null)
            {
                logger.debug("考核发布失败");
                jsonObject.put("error",1);
            }
            else
            {
                logger.debug("考核发布成功");
                jsonObject.put("error",0);
            }
            resp.getWriter().write(String.valueOf(jsonObject));
        }
        else if (oper.equals("getWorkDetails"))//获取作业详情方法
        {
            logger.debug("开始获取作业信息");
            String s=null;
            s=getWorkDetails(req,resp);
            logger.debug("成功获得信息:"+s);
            resp.getWriter().write(s);
        }
        else if (oper.equals("changeWorkPass"))//标记审核通过
        {
            logger.debug("开始执行changeWorkPass（）方法");
            String error=changeWorkPass(req,resp);
            JSONObject js=new JSONObject();
            js.put("error",error);
            resp.getWriter().write(String.valueOf(js));
        }
        else if (oper.equals("deleteWork"))//删除作业包括单个和批量。
        {
            logger.debug("开始执行deleteWork()方法");
            String error=deleteWorkArr(req,resp);
            JSONObject js=new JSONObject();
            js.put("error",error);
            resp.getWriter().write(String.valueOf(js));
        }
        else if (oper.equals("getTestDetails"))//获取考核详情方法。
        {
            String test=null;
            logger.debug("开始获取考核信息");
            test=getTestDetails(req,resp);
            if (test!=null)
                logger.debug("成功获得test："+test);
            else
                logger.debug("获取信息失败");
            resp.getWriter().write(test);
        }
        else if (oper.equals("getTestNoticeByTest"))//通过考核码获取作业详情方法。
        {
            String test=null;
            test=req.getParameter("test");
            logger.debug("开始执行getTestNoticeByTest方法");
            String result=null;
            if (test!=null)
            {
                result=getTestNoticeBytest(test);
            }
            if (result==null)
            {
                JSONObject js=new JSONObject();
                js.put("error","1");
                logger.debug(js);
                resp.getWriter().write(String.valueOf(js));
            }
            else
            {
                JSONObject js=new JSONObject(result);
                js.put("error","0");
                logger.debug(js);
                resp.getWriter().write(String.valueOf(js));
            }
        }
        else if (oper.equals("changeTestNotice"))//修改考核通知
        {
            logger.debug("开始执行changeTestNotice（）方法");
            String error=changeTestNotice(req,resp);
            JSONObject js=new JSONObject();
            js.put("error",error);
            resp.getWriter().write(String.valueOf(js));
        }
        else if (oper.equals("changeTestNoticeState"))//更改考核是否通过方法。
        {
            logger.debug("开始执行changeTestNoticeState（）方法");
            String error=changeTestNoticeState(req,resp);
            JSONObject js=new JSONObject();
            js.put("error",error);
            resp.getWriter().write(String.valueOf(js));
        }
        else if (oper.equals("deleteTestNotice"))//删除考核通知方法。
        {
            logger.debug("开始执行deleteTestNotice（）方法");
            String error=deleteTestNoticeArr(req,resp);
            JSONObject js=new JSONObject();
            js.put("error",error);
            resp.getWriter().write(String.valueOf(js));
        }
        else if (oper.equals("useSession"))//后台页面使用session实现欢迎您。
        {
            logger.debug("userSession开始执行");
            User user=(User) req.getSession().getAttribute("user");
            JSONObject jsonObject=new JSONObject(user);
            logger.debug(jsonObject);
            resp.getWriter().write(String.valueOf(jsonObject.get("uname")));
        }
        else
        {
            logger.debug("没有此操作");
        }
    }

/*
     参数：HttpServletRequest,HttpServletResponse。
     功能：发布考核方法。。
     返回值：Notice 对象。
*/
    private Notice giveTestNotice(HttpServletRequest req, HttpServletResponse resp)
    {
        logger.debug(" 获取请求数据");
//        获取请求数据
        Notice notice=new Notice();
        notice.settName(req.getParameter("tName"));
        notice.settContent(req.getParameter("tContent"));
        notice.setDeadline(req.getParameter("time"));
        notice.setTest(req.getParameter("test"));
//        调用service层方法
        logger.debug(" 调用service层方法");
        GiveTestNoticeService gtns=new ManagerServiceImpl();
        notice=gtns.giveTestNotice(notice);
        return notice;
    }

    /*
         参数：HttpServletRequest,HttpServletResponse。
         功能：获取作业详情方法。
         返回值：使用Json对象的总页数和Student对象数组。
    */
    private String  getWorkDetails(HttpServletRequest req, HttpServletResponse resp)
    {
//        声明Student数组对象。
        Student[] s=new Student[NUMS];
        String page=req.getParameter("page");
        String test=req.getParameter("test");
////        判断是否需要作业总量
//        Boolean CAPages=req.getParameter("CAPages").equals("1")?true:false;
        boolean CAPages=true;

        logger.debug("声明对象，调用service方法");
        return (new ManagerServiceImpl()).getWorkDetailsService(page,NUMS,CAPages,test);
    }

    /*
         参数：HttpServletRequest,HttpServletResponse。
         功能：获取作业详情方法。
         返回值：使用Json对象的总页数和Student对象数组。
    */
    private String getTestDetails(HttpServletRequest req, HttpServletResponse resp)
    {
        logger.debug("开始执行getTestDetails方法");
        Notice[] s=new Notice[NUMS];
        String page=req.getParameter("page");
//        判断是否需要作业总量
        Boolean CAPages=req.getParameter("CAPages").equals("1")?true:false;

        logger.debug("声明对象，调用service方法");
        return (new ManagerServiceImpl()).getTestDetailsService(page,NUMS,CAPages);
    }

    /*
     参数：String test。
     功能：通过考核码获取考核通知方法。
     返回值：使用Json对象的总页数和Student对象数组。
    */
    private String getTestNoticeBytest(String test)
    {
        logger.debug("调用getTestNoticeByTestService");
        ManagerServiceImpl ms=new ManagerServiceImpl();
        return ms.getTestNoticeByTestService(test);
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：更改考核通知方法。
     返回值：使用Json对象的总页数和Student对象数组。
    */
    private String changeTestNotice(HttpServletRequest req, HttpServletResponse resp)
    {
        Notice notice=new Notice();
        notice.setTest(req.getParameter("test"));
        notice.settName(req.getParameter("tName"));
        notice.settContent(req.getParameter("tContent"));
        logger.debug("period:"+req.getParameter("period"));
        notice.setPeriod(req.getParameter("period"));
        ManagerServiceImpl ms=new ManagerServiceImpl();
        logger.debug(notice);
        return ms.changeTestNotice(notice);
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：更改考核通知是否结束。
     返回值：error的json对象。
    */
    private String changeTestNoticeState(HttpServletRequest req, HttpServletResponse resp)
    {
        String test=req.getParameter("test");
        String state=req.getParameter("state");
        ManagerServiceImpl ms=new ManagerServiceImpl();
        return ms.changeTestNoticeStateService(test,state);
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：更改作业是否通过。
     返回值：error的json对象。
    */
    private String changeWorkPass(HttpServletRequest req, HttpServletResponse resp)
    {
        String pass=req.getParameter("pass");
        String wid=req.getParameter("wid");
        ManagerServiceImpl ms=new ManagerServiceImpl();
        return ms.changeWorkPassService(pass,wid);
    }
    /*
     参数：String wid。
     功能：删除上交的作业。
     返回值：error的json对象。
    */
    private String deleteWork(String wid)
    {
        logger.debug("开始执行deleteWork方法");
        logger.debug("开始本地文件的删除");
        ManagerServiceImpl ms=new ManagerServiceImpl();
        Student s = new FileServiceImpl().getFilePath(wid);
        logger.debug(s);
        String path = s.getFilePath();
        File temp=new File(path);
        if (temp.exists())
        {
            temp.delete();
        }
        logger.debug("开始数据库数据的删除");
        return ms.deleteWork(wid);
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：处理删除多个发布的通知。
     返回值：error的json对象。{error:0}或{error:1}
    */
    private String deleteTestNoticeArr(HttpServletRequest req, HttpServletResponse resp)
    {
        String data=req.getParameter("test");
        JSONArray json=new JSONArray(data);
        String error=null;

        for (int i=0;i<json.length();i++)
        {
            JSONObject js=json.getJSONObject(i);
            error=deleteTestNotice(String.valueOf(js.get("code")));
        }
        return error;
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：删除发布的通知。
     返回值：error的json对象。{error:0}或{error:1}
    */
    private String deleteTestNotice(String test)
    {
        ManagerServiceImpl ms=new ManagerServiceImpl();
        return ms.deleteTestNotice(test);
    }

    /*
     参数：HttpServletRequest req, HttpServletResponse resp。
     功能：处理删除多个发布的通知。
     返回值：error的json对象。{error:0}或{error:1}
    */
    private String deleteWorkArr(HttpServletRequest req, HttpServletResponse resp)
    {
        String data=req.getParameter("wid");
        logger.debug(data);
        JSONArray json=new JSONArray(data);
        logger.debug(json+"  "+json.length());
        String error=null;

        for (int i=0;i<json.length();i++)
        {
            JSONObject js=json.getJSONObject(i);
            error=deleteWork(String.valueOf(js.get("code")));
        }
        return error;
    }
}
