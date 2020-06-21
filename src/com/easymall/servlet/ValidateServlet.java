package com.easymall.servlet;

import com.easymall.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 冷夜雨花未眠
 * @create 2020/3/12 0012-16:29
 *
 * 创建验证码
 */
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //控制浏览器不使用缓存
        response.setDateHeader("Expires",-1);
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        //向页面画验证码
        VerifyCode vc = new VerifyCode();
        vc.drawImage(response.getOutputStream());
        String code = vc.getCode();
        //session域中添加验证码
        request.getSession().setAttribute("code",code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
