package com.my.service.imp;

import com.my.dao.ChangeTestNotice;
import com.my.dao.impl.LoginDao;
import com.my.pojo.Notice;
import com.my.pojo.Student;
import com.my.service.*;
import com.my.servlet.LoginServlet;
import org.apache.log4j.Logger;

import java.util.Calendar;

public class ManagerServiceImpl implements GiveTestNoticeService, GetWorkDetailsService, GetTestDetailsService, ChangeTestNoticeService, ChangeWorkPassService,DeleteWorkService
{
    Logger  logger=Logger.getLogger(ManagerServiceImpl.class);
    @Override
    public Notice giveTestNotice(Notice notice)
    {
        LoginDao ld=new LoginDao();
        return ld.giveTestNoticeDao(notice);
    }

    @Override
    public String  getWorkDetailsService(String page,int n,boolean CAPages,String test)
    {
        LoginDao ld=new LoginDao();
        return ld.getWorkDetailsDao(page,n,CAPages,test);
    }


    @Override
    public String getTestDetailsService(String page, int n, boolean CAPages)
    {
        LoginDao ld=new LoginDao();
        return ld.getTestDetailsDao(page,n,CAPages);
    }

    public String changeTestNotice(Notice notice)
    {
        LoginDao ld=new LoginDao();
        String sql="UPDATE t_test SET tname='"+notice.gettName()+"'," +
                "tcontent='"+notice.gettContent()+"', " +
                "tDeadline='"+notice.gettDeadline()+"', " +
                "period='"+notice.getPeriod()+"' WHERE test='"+notice.getTest()+"'";
        logger.debug(sql);
        return ld.changeTable(sql);
    }

    public String changeTestNoticeStateService(String test, String state)
    {
        LoginDao ld=new LoginDao();
        String sql="UPDATE  t_test SET state="+state+" WHERE test="+test+" ";
        logger.debug(sql);
        return ld.changeTable(sql);
    }

    @Override
    public String deleteTestNotice(String test)
    {
        LoginDao ld=new LoginDao();
        String sql="DELETE FROM t_test WHERE test="+test;
        logger.debug(sql);
        return ld.changeTable(sql);
    }

    @Override
    public String changeWorkPassService(String pass,String wid)
    {
        LoginDao ld=new LoginDao();
        String sql="UPDATE t_work SET pass='"+pass+"' WHERE wid="+wid+"";
        logger.debug("sql");
        return ld.changeTable(sql);
    }

    public String getTestNoticeByTestService(String test)
    {
        LoginDao ld=new LoginDao();
        String sql="SELECT * FROM t_test WHERE test="+test;
        logger.debug(sql);
        return ld.getTestNoticeBytestDao(sql);
    }


    @Override
    public String deleteWork(String wid)
    {
        logger.debug("开始执行deleteWork方法（service）");
        LoginDao ld=new LoginDao();
        String sql="DELETE FROM t_work WHERE wid="+wid;
        logger.debug("deleteWork:"+sql);
        return ld.changeTable(sql);
    }


}
