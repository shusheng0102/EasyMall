package com.easymall.servlet;

import com.easymall.utils.JDBCUtils;
import com.easymall.utils.WebUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

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
 * @create 2020/3/9 0009-11:34
 * 注册功能
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
      //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String valistr = request.getParameter("valistr");
        //System.out.println("username:"+username);

      //校验--非空+密码一致性+邮箱格式+验证码（TODO:）
        //if(username==null||"".equals(username)){
        if(WebUtils.isNull(username)){
            //如果满足，非空校验，则应该向浏览器输出错误提示。
            //通过请求转发和域对象实现。
            //向request域对象身上设置域属性
            request.setAttribute("msg","用户名不能为空");
            //请求转发
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            //由于请求转发前后的代码可以继续执行，
            // 所以应该在此处打断，避免发生校验，仍然注册跳转。
            return;
        }
        if(WebUtils.isNull(password)){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(WebUtils.isNull(password2)){
            request.setAttribute("msg","确认密码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(WebUtils.isNull(nickname)){
            request.setAttribute("msg","昵称不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        if(WebUtils.isNull(email)){
            request.setAttribute("msg","邮箱不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
            //密码一致性
        if(!password.equals(password2)){
            request.setAttribute("msg","两次密码不一致");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
            //邮箱格式校验
        String reg = "\\w+@\\w+(\\.\\w+)+";
        if (!email.matches(reg)){
            request.setAttribute("msg","邮箱格式不正确");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //6.验证码校验
        String code = (String) request.getSession().getAttribute("code");
        if (!code.equalsIgnoreCase(valistr)){
            request.setAttribute("msg","验证码错误");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
            return;
        }
        //7.注册用户--JDBC
//        创建连接池对象
        //ComboPooledDataSource source=new ComboPooledDataSource();

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //conn=source.getConnection();
            conn= JDBCUtils.getConnection();
            //插入数据前先判断当前数据库中是否存在用户？
            ps=conn.prepareStatement("select * from user where username=?");
            ps.setString(1,username);
            rs=ps.executeQuery();
            if(rs.next()){//true,用户名已存在，做出提示
                request.setAttribute("msg","用户名已存在");
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
                return;
            }else{//用户名不存在
                ps=conn.prepareStatement("insert into user values(null,?,?,?,?)");
                ps.setString(1,username);
                ps.setString(2,password);
                ps.setString(3,nickname);
                ps.setString(4,email);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn,ps,null);
        }
        //回到首页
        response.getWriter().write("<h1 align='center'><font color='red'>恭喜注册成功，3秒之后跳转回首页</font></h1>");
        response.setHeader("refresh","3;url=http://www.easymall.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
