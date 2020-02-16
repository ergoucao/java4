package com.my.dao.impl;

import com.my.dao.*;
import com.my.pojo.Notice;
import com.my.pojo.Student;
import com.my.utils.jdbc.JdbcUtils;
import com.my.pojo.User;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;

public class LoginDao implements ChangeTestNotice, ChangeTable, GetFilePath, GetTestDetailsDao, GetTestNoticeBytestDao
{
    Logger logger = Logger.getLogger(LoginDao.class);

    public LoginDao()
    {
    }

    //        创建数据库连接对象
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //利用账户密码在数据库查找
    public User checkLoginDao(String uname, String pwd)
    {

        //数据存储对象
        User u = null;
        //创建sql命名。
        String sql = "select * from t_user where uname=? and pwd=?";

        try
        {
            conn = JdbcUtils.getConnection();
//            创建sql命名对象
            ps = conn.prepareStatement(sql);
//            给占位符赋值
            ps.setString(1, uname);
            ps.setString(2, pwd);
//           执行
            rs = ps.executeQuery();
            while (rs.next())
            {
                u = new User();
                u.setUid(rs.getInt("Uid"));
                u.setPwd(rs.getString("pwd"));
                u.setUname(rs.getString("uname"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JdbcUtils.release(conn, ps, rs);
        }
        return u;
    }


    //利用uid在数据库查找
    public User checkLoginDao(String uid)
    {
        //数据存储对象
        User u = null;
        //创建sql命名。
        String sql = "select * from t_user where uid=?";

        try
        {
            conn = JdbcUtils.getConnection();
//            创建sql命名对象
            ps = conn.prepareStatement(sql);
//            给占位符赋值
            ps.setString(1, uid);
//           执行
            rs = ps.executeQuery();
            while (rs.next())
            {
                u = new User();
                u.setUid(rs.getInt("Uid"));
                u.setPwd(rs.getString("pwd"));
                u.setUname(rs.getString("uname"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JdbcUtils.release(conn, ps, rs);
        }
        return u;
    }


    public Student fileUpload(Student s)
    {
        logger.debug("开始执行filedao层filesave方法");
//        创建连接对象
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps = null;
        ResultSet rs1 = null;
        ResultSet rs = null;

//        创建sql语句
//          sql1查询学生信息
        String sql1 = "SELECT * FROM t_student WHERE studentid=?";
//          sql2插入学生信息
        String sql2 = "INSERT INTO t_student VALUES(DEFAULT,?,?);";
//          sql3查询作业信息
        String sql3 = "SELECT * FROM t_work WHERE wid=?";
//          sql4f插入作业信息4；
        String sql4 = "INSERT INTO t_work VALUES(DEFAULT,?,?,?,?,?);\n";


        try
        {
            logger.debug("正在执行执行filedao层filesave方法");
            conn = JdbcUtils.getConnection();
//            查询学生信息表是否已经存在当前学生。
            ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, s.getStudentId());
            rs1 = ps1.executeQuery();
            logger.debug("正在执行执行filedao层filesave方法1");
            if (!(rs1.next()))
            {
//                若不存在进行插入。
                logger.debug("正在执行执行filedao层filesave方法2");
                ps = conn.prepareStatement(sql2);
                logger.debug(s.getStudentId());
                ps.setString(1, s.getStudentId());
                ps.setString(2, s.getStudentName());

                int num = ps.executeUpdate();
                if (num > 0)
                {
                    logger.debug("学生数据插入成功");
                } else
                {
                    logger.debug("学生数据插入失败");
                    s = null;
                }
                logger.debug("正在执行执行filedao层filesave方法4");
//                查询学生信息中sid用于存储作业表中
                rs1 = ps1.executeQuery();
                ps = conn.prepareStatement(sql4);
            } else
            {
                logger.debug("正在执行执行filedao层filesave方法5");
//                学生表已经存在该学生。
                logger.debug(s.getStudentName() + "再次提交了作业");
                ps = conn.prepareStatement(sql4);
                ps.setString(2, rs1.getString("sid"));
            }

//            存储作业信息到数据库中。
            logger.debug("正在执行执行filedao层filesave方法6");

            ps.setString(1, s.getFilePath());
//            注意使用jdbc时需要用rs.next()将指针调到第3行。
            if (rs1.next())
            {
                ps.setString(2, rs1.getString("sid"));
                logger.debug("方法6：获得了sid");
            }
            ps.setString(3, s.getTest());
            ps.setString(4, s.getSubTime());
            ps.setString(5,"-1");
            int num = ps.executeUpdate();
            if (num > 0)
            {
                logger.debug(s.getStudentName() + "作业插入成功");
            } else
            {
                logger.debug(s.getStudentName() + "作业插入失败");
                s = null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            logger.debug("正在执行执行filedao层filesave方法7");
            JdbcUtils.release(conn, ps, rs);
            JdbcUtils.release(conn, ps1, rs1);
            return s;
        }
    }

    public Student getFilePath(String wid)
    {
        logger.debug("开始执行getFilePath(方法)");
//        创建sql语句
        String sql = "SELECT * FROM t_work WHERE wid=?;";
//        创建Student对象
        Student s = new Student();

//         获取连接对象
        try
        {
            logger.debug("执行getFilePath1");
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, wid);
            logger.debug("执行getFilePath2"+wid);
            rs = ps.executeQuery();
            if (rs.next())
            {
                logger.debug("执行getFilePath3");
                s.setWid(rs.getString("wid"));
                s.setTest(rs.getString("test"));
                s.setFilePath(rs.getString("filepath"));
                s.setsId(rs.getString("sid"));
            }
            logger.debug("执行getFilePath4"+s);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JdbcUtils.release(conn, ps, rs);
            logger.debug("执行完getFilePath(方法)获得Student:" + s);
            return s;
        }
    }

    public Notice giveTestNoticeDao(Notice notice)
    {
        logger.debug("开始执行giveTestNoticeDao方法 ");
//        创建sql语句
        String sql = "INSERT INTO t_test VALUE(DEFAULT,?,?,?,?);";

        try
        {
            conn = JdbcUtils.getConnection();
            logger.debug("开始执行sql方法1");
            ps = conn.prepareStatement(sql);
            ps.setString(1, notice.gettName());
            logger.debug("开始执行sql方法2");
            ps.setString(2, notice.gettContent());
            logger.debug("开始执行sql方法3");
            ps.setString(3, notice.gettDeadline());
            logger.debug("开始执行sql方法4");
            ps.setString(4, notice.getTest());
            logger.debug("开始执行sql方法5");
            int num = ps.executeUpdate();
            if (num > 0)
            {
                logger.debug(notice + ":插入成功");
            } else
            {
                logger.debug(notice + ":插入失败");
                notice = null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JdbcUtils.release(conn, ps, rs);
            return notice;
        }
    }

    public String getWorkDetailsDao(String page, int n, boolean CAPages,String test)
    {
        logger.debug("开始执行getWorkDetailsDao方法");
//          声明Student[]数组。
        Student[] s = new Student[n + 5];
//        读取不需要的数据。
        Student notNeed = new Student();
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
//        通过page确认需要的的work;
        int p = Integer.parseInt(page);
        int begin = (p - 1) * n;
//        声明i可用于统计总数。
        int i=0,tempN=n,j=0;
//        创建sql语句
        String sql = "SELECT * FROM t_work WHERE test=?";
        String sql1 = "SELECT *  FROM t_student WHERE sid=?";

        try
        {
            logger.debug("获取其他链接对象");
//            声明其他连接对象。
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,test);
            ps1 = conn.prepareStatement(sql1);
//            执行数据库查询
            logger.debug("执行数据库查询");
            rs = ps.executeQuery();
            for (i = 0; rs.next() && (tempN>=0 || CAPages == true); j++)
            {
                logger.debug("数据库查询提取中");
                logger.debug(begin+"  "+tempN);
                if (begin-- <= 0 && tempN-- > 0)
                {
                    s[i]=new Student();
                    logger.debug("数据库查询提取中1");
                    s[i].setWid(rs.getString("wid"));
                    logger.debug("数据库查询提取中2");
                    s[i].setFilePath(rs.getString("filepath"));
                    logger.debug("数据库查询提取中3");
                    s[i].setsId(rs.getString("sid"));
                    s[i].setTest(rs.getString("test"));
                    logger.debug("数据库查询提取中4");
                    s[i].setSubTime(rs.getString("subTime"));
                    s[i].setPass(rs.getString("pass"));
                    logger.debug(s[i].getsId());
                    ps1.setString(1,s[i].getsId());
                    rs1=ps1.executeQuery();
                    logger.debug("数据库查询提取中5");
                    if (rs1.next())
                    {
                        s[i].setStudentId(rs1.getString("studentid"));
                        s[i].setStudentName(rs1.getString("studentname"));
                    }
                    logger.debug("数据库查询提取中6");
                    i++;
                }
                logger.debug("数据提取中："+s[(i-1)<0?0:i-1]+" "+(i-1));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            JdbcUtils.release(conn,ps,rs);
            JdbcUtils.release(conn,ps1,rs1);
            return outASJson(s,i,n,j);
        }
    }

    //  将Studnet[]对象数据转化为json对象。
    private String outASJson(Student[] s,int i,int n,int j)
    {
        JSONArray out=new JSONArray();
        JSONObject jsCAPages=new JSONObject();
        logger.debug("j:"+j+"n:"+n);
        logger.debug((j+n)/n);//+((j%8==0||j<n)?1:0)
        jsCAPages.put("CAPages",(j)/n+1);//((j%n!=0||j==0||j%n==0)?1:0)
        out.put(jsCAPages);
        for (int k=0;k<i&&k<n;k++)
        {
            JSONObject temp=new JSONObject(s[k]);
            logger.debug(String.valueOf(temp));
            out.put(temp);
        }
        logger.debug(out);
        return String.valueOf(out);
    }

    public String getTestDetailsDao(String page, int n, boolean CAPages)
    {
        logger.debug("开始执行getTestDetailsDao方法");
//          声明Notice[]数组。
        Notice[] s = new Notice[n + 5];
//        读取不需要的数据。
        Notice notNeed = new Notice();
//        通过page确认需要的的work;
        int p = Integer.parseInt(page);
        int begin = (p - 1) * n;
//        声明i可用于统计总数。
        int i=0,tempN=n,j=0;
//        创建sql语句
        String sql = "SELECT * FROM t_test";
        try
        {
            logger.debug("获取其他链接对象");
//            声明其他连接对象。
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
//            执行数据库查询
            logger.debug("执行数据库查询");
            rs = ps.executeQuery();
            for (i = 0; rs.next() && (tempN>=0 || CAPages == true);j++ )
            {
                logger.debug("数据库查询提取中");
                logger.debug(begin+"  "+tempN);
                if (begin-- <= 0 && tempN-- > 0)
                {
                    s[i]=new Notice();
                    logger.debug("数据库查询提取中1");
                    s[i].setTest(rs.getString("test"));
                    logger.debug("数据库查询提取中2"+rs.getString("tDeadline"));
                    s[i].settDeadline(rs.getString("tDeadline"));
                    logger.debug("数据库查询提取中3");
                    s[i].settContent(rs.getString("tcontent"));
                    logger.debug("数据库查询提取中4");
                    s[i].settName(rs.getString("tname"));
                    logger.debug("数据库查询提取中5:");
                    s[i].settId(rs.getString("tid"));
                    logger.debug("数据库查询提取中6");
                    s[i].setPeriod(rs.getString("period"));
                    logger.debug("数据库查询提取中7");
                    s[i].setState(rs.getString("state"));
                    i++;
                }
                logger.debug("数据提取中("+i+")："+s[i]);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            JdbcUtils.release(conn,ps,rs);
            return outASJson(s,i,n,j);
        }
    }

    //  将NOtice[]对象数据转化为json对象。
    private String outASJson(Notice[] s,int i,int n,int j)
    {
        JSONArray out=new JSONArray();
        JSONObject jsCAPages=new JSONObject();
        logger.debug("j:"+j+"n:"+n);
        logger.debug((j)/n+((j%n==0||j<n)?1:0));//(j+n)/n+((j%8==0||j<n)?1:0)
        jsCAPages.put("CAPages",(j)/n+1);//(j+n)/n+((j%8==0||j<n)?1:0)((j%n!=0)?1:0)
        out.put(jsCAPages);
        for (int K=0;K<i&&K<n;K++)
        {
            logger.debug("outAsJSon+"+s[K]);
            JSONObject temp=new JSONObject(s[K].toString());
            logger.debug(String.valueOf(temp));
            out.put(temp);
        }
        logger.debug(out);
        return String.valueOf(out);
    }

    @Override
    public String changeTable(String sql)
    {
        String  error=null;
        try
        {
            conn=JdbcUtils.getConnection();
            ps=conn.prepareStatement(sql);
            int num=ps.executeUpdate();
            if (num>0)
            {
                logger.debug("更改成功");
                error="0";
            }
            else
            {
                logger.debug("更改失败");
                error="1";
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            JdbcUtils.release(conn,ps,rs);
            return error;
        }
    }


    public String getTestNoticeBytestDao(String sql)
    {
        String  error=null;
        logger.debug("进入getTestNoticeBytestDao方法");
        Notice s=null;
        try
        {
            conn=JdbcUtils.getConnection();
            ps=conn.prepareStatement(sql);
            logger.debug("sql:"+sql);
            rs=ps.executeQuery();
            if (rs.next())
            {
                s=new Notice();
                logger.debug("数据库查询提取中1");
                s.setTest(rs.getString("test"));
                logger.debug("数据库查询提取中2"+rs.getString("tDeadline"));
                s.settDeadline(rs.getString("tDeadline"));
                logger.debug("数据库查询提取中3");
                s.settContent(rs.getString("tcontent"));
                logger.debug("数据库查询提取中4");
                s.settName(rs.getString("tname"));
                logger.debug("数据库查询提取中5:");
                s.settId(rs.getString("tid"));
                logger.debug("数据库查询提取中6");
                s.setPeriod(rs.getString("period"));
                logger.debug("数据库查询提取中7");
                s.setState(rs.getString("state"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally
        {
            JdbcUtils.release(conn,ps,rs);
            logger.debug("dao层执行完："+s);
            return s!=null?String.valueOf(new JSONObject(s.toString())):null;
        }
    }

    public static void main(String[] args)
    {
        LoginDao ld=new LoginDao();
        System.out.println(ld.checkLoginDao("zhangsan","123"));
    }
}