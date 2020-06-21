<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/9 0009
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="css/login.css"/>
    <title>EasyMall欢迎您登陆</title>
</head>
<body>
<h1>欢迎登陆EasyMall</h1>
<form action="<%=request.getContextPath()%>/LoginServlet" method="POST">
    <%
        Cookie[] cookies = request.getCookies();
        //初次获取cookies为null，循环遍历时，排除为null的情况
        Cookie remnameC=null;
        if (cookies!=null){
            for (Cookie c:cookies){
                if("remname".equals(c.getName())){
                    remnameC=c;
                }
            }
        }
        String username="";
        if(remnameC!=null){
            username = URLDecoder.decode(remnameC.getValue(),"utf-8"); //username
        }
    %>
    <table>
        <tr>
            <td class="tdx" style="color:red;text-align: center" colspan="2">
                <%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
            </td>
        </tr>
        <tr>
            <td class="tdx">用户名：</td>
            <td><input type="text" name="username" value="<%=username%>" /></td>
        </tr>
        <tr>
            <td class="tdx">密&nbsp;&nbsp; 码：</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="remname" value="true"
                        <%=username.equals("")?"":"checked='checked'"%>
                        />记住用户名
                <input type="checkbox" name="autologin" value="true"/>30天内自动登陆
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align:center">
                <input type="submit" value="登 陆"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>


