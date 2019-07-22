<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
    buffer="none"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" >
<html>
    <head>
        <meta http-equiv="Content-Type"
            content="text/html; charset=utf-8" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Cache-Control" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <title>用户登录</title>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" type="text/css"></link>
    </head>
    <body onkeydown=keyLogin();>
        <div id="login">
            <div id="top">
                <div id="top_left">
                    <img src="${pageContext.request.contextPath }/images/login_03.png" />
                </div>
                <div id="top_center"></div>
            </div>
            <div id="center">
                <div id="center_left"></div>
                <div id="center_middle">
                    <form id="loginForm"
                        action='${pageContext.request.contextPath}/loginaction'
                        method='post'>
                        <div class="center_middle_m">
                            <span id="userNameyz">${message}</span>
                        </div>
                        <div id="user">用户名：</div>
                        <div class="_input"><input type='text' id="username" class="W_input" name='emp.name' placeholder="员工姓名"/></div>
                        <div id="pass">密 码：</div>
                        <div class="_input">
                            <input type='password' id="password" class="W_input" name='emp.password' placeholder="密码"/>
                        </div>
                        <div>
                            <input type="submit" id="sub" class="btn"
                                value="登录" />
                            <input type="reset" value="取消" class="btn" />
                        </div>
                        <input type="hidden" name="hidesd" value="start" />
                    </form>
                </div>
                <div id="center_right"></div>
            </div>
        </div>
    </body>
</html>
