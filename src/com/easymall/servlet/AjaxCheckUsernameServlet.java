package com.easymall.servlet;

import com.easymall.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/9 0009-21:49
 *
 *          ajax
 */
@WebServlet("/AjaxCheckUsernameServlet")
public class AjaxCheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.请求响应乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        //1.JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn= JDBCUtils.getConnection();
            ps=conn.prepareStatement("select * from user where username=?");
            ps.setString(1,username);
            rs = ps.executeQuery();
            if(rs.next()){//true代表用户名已存在，不可以使用
                //2.响应至浏览器中
                response.getWriter().write("用户名已存在");
            }else{//false代表用户名不存在，可以使用
                response.getWriter().write("用户名可以使用");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally{
            JDBCUtils.close(conn,ps,rs);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
