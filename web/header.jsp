<%@page import="java.util.ArrayList"%>
<%@page import="java.net.CookieManager"%>
<%@page import="java.net.CookieHandler"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PTIT LEARNING</title>
        <link rel="stylesheet" type="text/css" href="./css/style.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns"
              crossorigin="anonymous">


        <!--<script src="https://apis.google.com/js/client:platform.js?onload=renderButton" async defer></script>-->
        <meta name="google-signin-client_id" content="785882447208-cnnkdb3q86glg81ftiil2tdovtugsqrg.apps.googleusercontent.com">

        <script type="text/javascript" src="./js/jquery.js"></script>
        <script type="text/javascript" src="./js/javascript.js"></script>
    </head>
    <body>
        <%@page import="javax.servlet.http.HttpSession" %>        
        <%  int i; // i = 1 la da dang nhap, i = 0 la chua dang nhap
            String user = null;
            String pass = null;
            session = request.getSession();
            if (session.isNew()) {
                session.setAttribute("user", "");
                i = 0;
            } else {
                try {
                    user = session.getAttribute("user").toString();
                    pass = session.getAttribute("pass").toString();
                    if (user.equals("")) {
                        i = 0;
                    } else {
                        i = 1;
                    }
                } catch (Exception e) {
                    i = 0;
                    session.setAttribute("user", "");
                }
            }
        %>
        <header id="header">
            <div class ="wrap">
                <a href="home"><div id="logo"></div></a><% //
                    if (i == 0) {%>
                <button id="profile" class="text">Đăng Nhập</button>
                <% } else {%>
                <div id='admin' class="text">
                    <%=session.getAttribute("name").toString()%>
                </div>
                <div id="signout" class="text">Đăng xuất</div>
                <% }
                %>
                <div id="search_area">
                    <input type="text" name="text_search" id="text_search" placeholder="type content to search" class="text">
                    <button id="search" class="text">Tìm kiếm</button>
                </div>
            </div>
        </header>
        <div class="wrap">
            <nav id="nav" class="nav2">
                <ul>
                    <li title="Trang chủ">
                        <a href="home">Trang Chủ</a>
                    </li>
                    <li title="Tiến trình dạy">
                        <a>Tiến Trình dạy</a>
                    </li>
                    <li title="Thư viện">
                        <a href="library">Thư viện</a>
                    </li>
                    <% if (i == 1) {%>
                    <li><a href ='NhapDiem'>Nhập Điểm</a></li>
                        <%} else {%>
                    <li><a id="nhapDiem">Nhập Điểm</a></li>
                        <%}%>
                    <li title="Cá nhân">
                        <a href="profile">Cá Nhân</a>
                    </li>
                </ul>
            </nav>
        </div>
        <div id="sign" class="main-content-agile">
            <div class="popup">
                <h2>Đăng Nhập</h2>
                <form method="post" id="aspnetForm" action="Login">
                    <div class="field mr-bottom">
                        <span class="fa fa-user" aria-hidden="true"></span>
                        <input placeholder="User" name="user" type="text" id="username"
                               required />
                    </div>
                    <div class="field">
                        <span class="fa fa-key" aria-hidden="true"></span>
                        <input placeholder="Password" name="pass" type="password" id="password"
                               required />
                    </div>
                    <p class="error">
                    </p>
                    <div class="sub">
                        <a id="ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_lnkBtnQuenMK" href="javascript:__doPostBack('ctl00$ContentPlaceHolder1$ctl00$ucDangNhap$lnkBtnQuenMK','')">Forgot password!</a>
                    </div>
                    <img id="loading" src="./images/loading.gif" width="30px" height="30px" style="margin-top: -10px">
                    <div class="submit">
                        <input type="submit" value="Sign In" id="dangNhap" />
                    </div>
                </form>
            </div>
        </div>
        <div id="linkTat">
            <a href="home"><div class="linkTat"><img src="./images/home.png"></div></a>
            <a href="https://www.facebook.com/groups/CnttandAttt/" target="blank"><div class="linkTat"><img src="./images/faceLogo.png"></div></a>
            <a href="http://qldt.ptit.edu.vn/" target="blank"><div class="linkTat linkTat2"><img src="./images/ptit.png"></div></a>
        </div>
        <div id="linkTop">
            <a href="#listNews"><img src="./images/arrow.png"></a>
        </div>
