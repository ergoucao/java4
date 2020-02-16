package com.my.pojo;

/*
   Notice对象：

 */


import java.text.SimpleDateFormat;
import java.util.Date;

public class Notice
{
    String tId=null;
    String tName=null;
    String tContent=null;
    String tDeadline =null;
    String test=null;
    String state=null;
    String period=null;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getPeriod()
    {
        return period;
    }

    public void setPeriod(String period)
    {
        this.period = period;
        setDeadline(period);
    }

    public String getTest()
    {
        return test;
    }

    public void setTest(String test)
    {
        this.test = test;
    }

    public String gettId()
    {
        return tId;
    }

    public void settId(String tId)
    {
        this.tId = tId;
    }

    public String gettName()
    {
        return tName;
    }

    public void settName(String tName)
    {
        this.tName = tName;
    }

    public String gettContent()
    {
        return tContent;
    }

    public void settContent(String tContent)
    {
        this.tContent = tContent;
    }

    public String gettDeadline()
    {
        if (tDeadline ==null)
        {
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tDeadline =sdf.format(date);
        }
        return tDeadline;
    }

    //输入时间间隔进行deadline的设置。
    public void setDeadline(String time)
    {
        int day=Integer.parseInt(time);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tDeadline =sdf.format(new Date(date.getTime()+day* 24 * 60 * 60 * 1000));
    }

    public void settDeadline(String time)
    {
        tDeadline=time;
    }

    @Override
    public String toString()
    {
        return "{" +
                "tId:'" + tId + '\'' +
                ", tName:'" + tName + '\'' +
                ", tContent:'" + tContent + '\'' +
                ", tDeadline:'" + tDeadline + '\'' +
                ", test:'" + test + '\'' +
                ", state:'" + state + '\'' +
                ", period:'" + period + '\'' +
                '}';
    }
}
