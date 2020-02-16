package com.my.service.imp;

import com.my.dao.impl.LoginDao;
import com.my.pojo.Student;
import com.my.service.FileService;
import com.my.service.GetFilePath;
import org.apache.log4j.Logger;

public class FileServiceImpl implements FileService, GetFilePath
{
    Logger logger=Logger.getLogger(FileServiceImpl.class);

    @Override
    public Student saveFile(Student s)
    {
        LoginDao ld=new LoginDao();
        logger.debug("调用了Dao层方法");
        s=ld.fileUpload(s);
        return s;
    }

    @Override
    public Student getFilePath(String wid)
    {
        LoginDao ld=new LoginDao();
        logger.debug("调用了Dao层getFilePath（）方法");
        Student path=ld.getFilePath(wid);
        return path;
    }
}
