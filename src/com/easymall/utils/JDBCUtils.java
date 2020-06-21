package com.easymall.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/9 0009-13:45
 * JDBC工具类
 */
public class JDBCUtils {
    private JDBCUtils() {
    }
    //初始化c3p0连接池对象
    public static ComboPooledDataSource source=new ComboPooledDataSource();

    //获取链接
    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }
    //关闭资源
    public static void close(Connection conn, Statement stat, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                rs=null;
            }
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                stat=null;
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                conn=null;
            }
        }
    }

}
