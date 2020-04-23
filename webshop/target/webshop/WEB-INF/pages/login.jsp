<!DOCTYPE html>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/bootstrap.jspf" %>
<%@include file="/WEB-INF/jspf/set_locale.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
   <meta charset="UTF-8">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <title>Web app</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../style/style.css" rel="stylesheet">
</head>
<body>


<div class="container my-2">


        <form action="/login" class="col-5" name="log-in" id="log" method="post">
            <h2>Login</h2>
            <div class="form-group">
                <label><fmt:message key="page.lang.login"/></label>
                <input type="text" class="form-control" name="login" placeholder="Enter login">
                <span id="login-error" style="color: red;">${errors['login-error']}</span>
                <span id="user-found-error" style="color: red;">${errors['user-found-error']}</span>

            </div>
            <div class="form-group">
                <label><fmt:message key="page.lang.password"/></label>
                <input type="password" class="form-control" name="password" placeholder="Enter password">
               <span id="password-error" style="color: red;">${errors['password-error']}</span>
            </div>
            <c:remove var="errors" scope="session"/>
            <button type="submit" id="log-submit" class="btn btn-outline-dark"><fmt:message key="page.lang.singin"/></button>
            <a href="/register" class="btn btn-outline-dark"><fmt:message key="page.lang.register"/></a>
        </form>


</div>

</body>
</html>