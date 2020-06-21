package com.easymall.servlet;

import com.easymall.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/11 0011-19:14
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //2.获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remname = request.getParameter("remname");
        //3.判断是否记住用户名
        if("true".equals(remname)){
            //利用cookie记住
            Cookie cookie = new Cookie("remname", URLEncoder.encode(username,"utf-8"));
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(60*60*24*30);//cookie保留30天
            response.addCookie(cookie);
        }else {//删除保存用户名的cookie
            //删除cookie就是创建一个与要删除cookie完全一致的cookie，且生命时长为0
            Cookie cookie = new Cookie("remname", "");
            cookie.setPath(request.getContextPath()+"/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        //4.访问数据库--JDBC
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn= JDBCUtils.getConnection();
            ps=conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){//如果返回值为true，则可以登录，保存登录状态，跳转至首页
                HttpSession session=request.getSession();
                session.setAttribute("username",username);//域中有此域属性表示有登录状态

            }else{//如果返回值为false，则不可以登录，向login.jsp页面返回错误信息。
                request.setAttribute("msg","用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }


        //5.用户名正确，则登录
        //6.用户错误，在页面中做出提示
        //7.跳转回首页
        response.sendRedirect("http://www.easymall.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
