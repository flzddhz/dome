package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class BeasDao {   //还未做完！！！！！！

    /**
     *
     * 操作数据库的基本类：其中三个属性提供了两个通用的方法，一个是获取连接的方法，一个是关闭资源的方法。
     *
     */
    public class BaseDao {
        //1.三个属性
        protected InputStream inputStream=null;
        protected Connection ct=null;
        protected PreparedStatement pst=null;
        protected ResultSet rs=null;
        //2.获取连接的方法
        protected void getConnection(){
            try {
                //通过类加载器加载了db.properties
                inputStream=this.getClass().getClassLoader().getResourceAsStream("db.properties");
                //通过properties类来读取db.properties中的每一个连接数据库的参数
                Properties pt=new Properties();
                //将保存了db.properties文件的输入流放置到properties的对象中去
                pt.load(inputStream);
                //通过getproperty（）方法读取db.properties中的4个数据库的参数
                String driverClass= pt.getProperty("driverClass");
                String jdbcUrl= pt.getProperty("jdbcUrl");
                String user= pt.getProperty("user");
                String password= pt.getProperty("password");
                //3.通过以上读取到的4个连接数据库的参数，然后获取一个数据库的连接。
                //注册驱动：将需要连接的数据库注册驱动class类中
                Class.forName(driverClass);
                //通过getConnection(jdbcUrl, user, password)的方法能获取到一与个数据库的连接
                ct= DriverManager.getConnection(jdbcUrl, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //3.关闭资源
        protected void closeAll(){
            try {
                if(rs!=null){
                    rs.close();
                }
                if(pst!=null){
                    pst.close();
                }
                if(ct!=null){
                    ct.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
