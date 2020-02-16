package com.my.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Student
{
    String sId =null;
    String studentId =null;
    String studentName =null;
    String test=null;
    String wid=null;
    String filePath =null;
    String fileName =null;
    String pass=null;
    String subTime=null;


    public String getSubTime()
    {
        if (subTime==null)
        {
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            subTime=sdf.format(date);
        }
        return subTime;
    }

    public void setSubTime(String subtime)
    {
        this.subTime = subtime;
    }

    public Student()
    {
    }

    public String getsId()
    {
        return sId;
    }

    public void setsId(String sId)
    {
        this.sId = sId;
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
    }

    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    public String getTest()
    {
        return test;
    }

    public void setTest(String test)
    {
        this.test = test;
    }

    public String getWid()
    {
        return wid;
    }

    public void setWid(String wid)
    {
        this.wid = wid;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(sId, student.sId) &&
                Objects.equals(studentId, student.studentId) &&
                Objects.equals(studentName, student.studentName) &&
                Objects.equals(test, student.test) &&
                Objects.equals(wid, student.wid) &&
                Objects.equals(filePath, student.filePath);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sId, studentId, studentName, test, wid, filePath);
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    @Override
    public String toString()
    {
        return "{" +
                "sid:'" + sId + '\'' +
                ", studentid:'" + studentId + '\'' +
                ", studentname:'" + studentName + '\'' +
                ", test:'" + test + '\'' +
                ", wid:'" + wid + '\'' +
                ", filepath:'" + filePath + '\'' +
                ", filename:'" + fileName + '\'' +
                ", pass:'" + pass + '\'' +
                ", subtime:'" + subTime + '\'' +
                '}';
    }

}
