<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/bootstrap.jspf" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Web app</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link href="../style/style.css" rel="stylesheet">
</head>
<body>
<hr/>
  <h3 align="center"> ${sessionScope.errors.getMessage()} </h2>

</body>
</html>