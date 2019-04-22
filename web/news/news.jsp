<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<jsp:include page="../header.jsp"></jsp:include>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="./css/news.css">
<div id="wrap" class="wrap">
    <main>
        <article>
            <%@page import="home.model.SubContent" %>
            <%
                SubContent subContent = (SubContent) request.getAttribute("subContent");
                String h1 = subContent.getH1();
                String h2 = subContent.getH2();
                Date date = new Date(subContent.getTime().getTime());
                SimpleDateFormat format = new SimpleDateFormat("HH : mm - dd/MM/yyyy");
                String time = format.format(date);
                String content = subContent.getContent();%>                
            <h1><%=h1%></h1>
            <p class="time"><%=time%></p>
            <h2><%=h2%></h2>
            <div class="contentDetail"><%=content%></div>
        </article>
        <aside>
            <ul id="giaoVu">

            </ul>
        </aside>				
    </main>
</div>
<script type="text/javascript" src="./news/news.js"></script>
<jsp:include page="../footer.jsp"></jsp:include>
