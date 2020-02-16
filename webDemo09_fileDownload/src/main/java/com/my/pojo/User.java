package com.my.pojo;

import java.util.Objects;

public class User
{
    private  int uid;
    private  String uname;
    private  String pwd;

    public int getUid()
    {
        return uid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  uname.equals(user.uname) &&
                pwd.equals(user.pwd);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(uname, pwd);
    }

    @Override
    public String toString()
    {
        return "{" +
                "uid:" + uid +
                ", uname:'" + uname + '\'' +
                ", pwd:'" + pwd + '\'' +
                '}';
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }
}
