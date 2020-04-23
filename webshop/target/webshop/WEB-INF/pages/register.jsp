<!DOCTYPE html>
<html lang="en">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/set_locale.jspf" %>
<%@include file="/WEB-INF/jspf/bootstrap.jspf" %>

<c:set var="login" value="${sessionScope.login}"/>
<c:set var="email" value="${sessionScope.email}"/>
<c:set var="lastName" value="${sessionScope.lastName}"/>
<c:set var="firstName" value="${sessionScope.firstName}"/>
<c:set var="errors" value="${sessionScope.errors}"/>

<head>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>Web app</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="../js/validator.js"></script>
    <link href="../style/style.css" rel="stylesheet">
</head>
<body>


<div class="container my-2">

    <form action="/register" method="post" class="col-5" name="register" id="reg" onsubmit="return formValidate();" enctype="multipart/form-data">
        <h2>Registration</h2>
        <div class="form-group">
            <label><fmt:message key="page.lang.login"/></label>
            <input type="text" class="form-control" name="login" placeholder="Enter login" value="${login}">
            <span id="login-error" style="color: red;">${errors['login-error']}</span>
            <span id="create-user-error" style="color: red;" >${errors['user-create-error']}</span>
        </div>
        <div class="form-group">
            <label><fmt:message key="page.lang.first.name"/></label>
            <input type="text" class="form-control" name="first-name" placeholder="Enter first name" value="${firstName}">
            <span id="first-name-error" style="color: red;">${errors['first-name-error']}</span>
        </div>
        <div class="form-group">
            <label><fmt:message key="page.lang.last.name"/></label>
            <input type="text" class="form-control" name="last-name" placeholder="Enter last name" value="${lastName}">
            <span id="last-name-error" style="color: red;">${errors['last-name-error']}</span>
        </div>
        <div class="form-group">
            <label><fmt:message key="page.lang.email"/></label>
            <input type="text" class="form-control" name="email" placeholder="Enter email" value="${email}">
            <span id="email-error" style="color: red;">${errors['email-error']}</span>
        </div>
        <div class="form-group">
            <label><fmt:message key="page.lang.password"/></label>
            <input type="password" class="form-control" name="password" placeholder="Enter password">
            <span id="password-error" style="color: red;">${errors['password-error']}</span>
        </div>
        <div class="form-group">
            <label><fmt:message key="page.lang.confirm.password"/></label>
            <input type="password" class="form-control" name="confirm-password" placeholder="Confirm password">
            <span id="confirm-password-error" style="color: red;">${errors['confirm-password-error']}</span>
        </div>
        <div class="form-group">
             <label><fmt:message key="page.lang.avatar"/></label>
             <input id="registration-image"  class="form-control-file" type="file" name="avatar"/>
             <span id="avatar-error-msg" style="color: red;">${errors['avatar-error-msg']}</span>
        </div>
        <div class="form-group">
        <label><fmt:message key="page.lang.captcha"/></label>
             <info:captcha captchaId="${CaptchaId}" image="${captchaImage}"/>
             <input type="text" class="form-control form-control-sm" name="confirm-captcha" width="200">
             <span id="captcha-error" style="color: red;">${errors['captcha-error']}</span>
        </div>
        <div class="form-group">
               <label><fmt:message key="page.lang.spam"/></label>
               <input type="checkbox" class="checkbox" name="spam">
               <span id="checkbox-error" style="color: red;">${errors['checkbox-error']}</span>
        </div>

        <c:remove var="errors" scope="session"/>
        <button type="submit" id="reg-submit" class="btn btn-outline-dark"><fmt:message key="page.lang.submit"/></button>
        <a href="/login" class="btn btn-outline-dark"><fmt:message key="page.lang.singin"/></a>
    </form>

</div>

</body>
</html>