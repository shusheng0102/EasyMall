<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/9 0009
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
    <div id="line1">
        <div id="content">
            <%--通过判断session域中有无域属性username来确定有无登录状态--%>
                <%
                    //先判断有无session对象，再判断有无username域属性
                    if(request.getSession(false)!=null &&
                            request.getSession().getAttribute("username")!=null){
                        //当session存在，且其中包含username域属性，则证明属于登录状态
                %>
                <a href="#">欢迎，<%=request.getSession().getAttribute("username")%>回来</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/LogOutServlet">注销</a>
                <%
                }else{
                %>
                <a href="<%=request.getContextPath()%>/login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/regist.jsp">注册</a>
                <%
                    }
                %>
        </div>
    </div>
    <div id="line2">
        <img id="logo" src="<%=request.getContextPath()%>/img/head/logo.jpg"/>
        <input type="text" name=""/>
        <input type="button" value="搜 索"/>
        <span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="#">我的购物车</a>
		</span>
        <img id="erwm" src="<%=request.getContextPath()%>/img/head/qr.jpg"/>
    </div>
    <div id="line3">
        <div id="content">
            <ul>
                <li><a href="#">首页</a></li>
                <li><a href="#">全部商品</a></li>
                <li><a href="#">手机数码</a></li>
                <li><a href="#">电脑平板</a></li>
                <li><a href="#">家用电器</a></li>
                <li><a href="#">汽车用品</a></li>
                <li><a href="#">食品饮料</a></li>
                <li><a href="#">图书杂志</a></li>
                <li><a href="#">服装服饰</a></li>
                <li><a href="#">理财产品</a></li>
            </ul>
        </div>
    </div>
</div>

