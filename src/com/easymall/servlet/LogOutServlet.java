package com.easymall.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/12 0012-16:12
 *
 * 注销servlet
 */
@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session，即为用户注销
        if(request.getSession(false) != null){//在session对象不为null的情况下
            //将session对象销毁。
            request.getSession(false).invalidate();
        }
        response.sendRedirect(request.getContextPath()+"/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
