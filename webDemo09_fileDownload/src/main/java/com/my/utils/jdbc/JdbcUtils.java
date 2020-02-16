package com.my.utils.jdbc;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils
{
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static
    {
        try
        {
            //读取db.properties文件的数据库连接信息
            InputStream in= JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties prop=new Properties();
            prop.load(in);

            driver=prop.getProperty("driver");
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("password");

            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /*
参数：无
功能：返回数据库连接对象
返回值：无
*/
    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

    public static Connection getConnection(String url) throws SQLException
    {
        return DriverManager.getConnection(url,username,password);
    }

    /*
 参数：Connection，Statement，ResultSet。
 功能：释放资源
 返回值：无
*/
    public static void release(Connection conn, Statement st, ResultSet rs)
    {
        if (rs!=null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (st!=null)
        {
            try
            {
                st.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (conn!=null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    /*
参数：Connection，PreparedStatement，ResultSet。
功能：释放资源
返回值：无
*/
    public static void release(Connection conn, PreparedStatement ps, ResultSet rs)
    {
        if (rs!=null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (ps!=null)
        {
            try
            {
                ps.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        if (conn!=null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
