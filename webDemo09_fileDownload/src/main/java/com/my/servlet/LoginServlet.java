package com.my.servlet;

import com.my.pojo.User;
import com.my.service.LoginService;
import com.my.service.imp.LoginServiceImpl;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet
{
    Logger logger=Logger.getLogger(LoginServlet.class);
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
//        设置请求编码格式
        req.setCharacterEncoding("utf-8");
//        设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
//        获取请求信息

        String uname=req.getParameter("adname");
        String pwd=req.getParameter("adid");
        String ck=req.getParameter("ck");
        String error=null;
        String oper=req.getParameter("oper");
//        使用cookie登录。
        if (oper.equals("ck"))
        {
            resp.getWriter().write(useCookie(req,resp));
            return ;
        }
//        处理请求信息
//             登录信息检验，获取业务层对象
        LoginService ls=new LoginServiceImpl();
        logger.debug(uname  + pwd);
        User u=ls.checkLoginService(uname,pwd);
//        响应请求
        logger.debug(u);
        if (u!=null)
        {
            logger.debug(ck);
            //创建COOKie实现免登陆
            if (ck!=null&&ck.equals("1"))
            {
                Cookie c=new Cookie("uid",u.getUid()+"");
//                三天免登录。
                c.setMaxAge(3*24*3600);
                resp.addCookie(c);
            }
            logger.debug("存入session");
//          将用户对象存入session 创建session对象
            HttpSession hs=req.getSession();
            hs.setAttribute("user",u);
            logger.debug("session:"+u);

//          利用servletContext实现网站访问计算器。
            ServletContext sc=req.getSession().getServletContext();
              error="0";
//            resp.sendRedirect("/workSubmssion_war_exploded/main.html");
        }
        else
        {
              error="1";
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("htmlName","Backstage.html");
        jsonObject.put("error",error);
        logger.debug(jsonObject);
        resp.getWriter().write(String.valueOf(jsonObject));
////            resp.sendRedirect("/webDemo09_fileDownload_Web_exploded/Backstage.html");
//            req.getRequestDispatcher("/webDemo09_fileDownload_Web_exploded/Backstage.html").forward(req,resp);
    }

    private String useCookie(HttpServletRequest req, HttpServletResponse resp)
    {
        Cookie[] cks = req.getCookies();
        String adid=null;
        String adname=null;

        if (cks != null)
        {
            String uid="";
            for (Cookie c:cks)
            {
                if ("uid".equals(c.getName()))
                {
                    uid=c.getValue();
                }
            }
            if (!("".equals(uid)))
            {
                //校验uid用户信息。
                LoginService ls=new LoginServiceImpl();
                User u=ls.checkLoginService(uid);
                if (u!=null)
                {
                    //将用户对象存入session 创建session对象
                    HttpSession hs=req.getSession();
                    hs.setAttribute("user",u);
                    logger.debug("session（cookie）:"+u);

                    //cookie检验成功。
                    adid=u.getPwd();
                    adname=u.getUname();
                }
            }
        }
        JSONObject js=new JSONObject();
        js.put("adid",adid);
        js.put("adname",adname);
        logger.debug(js);
        return String.valueOf(js);
    }
}
