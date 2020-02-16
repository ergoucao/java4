package com.my.service.imp;


import com.my.dao.impl.LoginDao;

import com.my.pojo.User;
import com.my.service.LoginService;

public class LoginServiceImpl implements LoginService
{
    LoginDao ld=new LoginDao();
    @Override
    public User checkLoginService(String uname, String pwd)
    {
        return ld.checkLoginDao(uname,pwd);
    }

    @Override
    public User checkLoginService(String uid)
    {
        return ld.checkLoginDao(uid);
    }

    public LoginServiceImpl()
    {

    }
}
