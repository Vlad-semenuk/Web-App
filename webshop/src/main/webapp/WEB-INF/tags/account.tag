<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/set_locale.jspf" %>
<%@ attribute name="user" required="true" %>
<%@ attribute name="avatar" required="true" %>


 <c:choose>
    <c:when test="${sessionScope.user eq null}">
       <li class="nav-item">
            <a class="nav-link" href="/login"><fmt:message key="page.lang.login"/></a>
       </li>
       <li class="nav-item">
            <a class="nav-link" href="/register"><fmt:message key="page.lang.register"/></a>
       </li>
    </c:when>
    <c:otherwise>

       <img src="avatar" width="73" height="31" title="${sessionScope.user.getFirstName()} ${sessionScope.user.getLastName()}" >
       <li ><a class="nav-link" href="/logout"><fmt:message key="page.lang.logout"/></a></li>
    </c:otherwise>
 </c:choose>