<%@ tag language="java" pageEncoding="UTF-8" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/set_locale.jspf" %>
 <c:choose>
    <c:when test="${sessionScope.locale eq null}">
      <fmt:setLocale value="${cookie.locale.value}"/>
      <fmt:setBundle basename="locale"/>
    </c:when>
    <c:otherwise>
       <fmt:setLocale value="${sessionScope.locale.getLanguage()}"/>

    </c:otherwise>
 </c:choose>

     <li class="nav-item">
            <a class="nav-link" href="${contextPath}?lang=en"><fmt:message key="page.lang.english"/></a>
     </li>
     <li class="nav-item">
             <a class="nav-link" href="${contextPath}?lang=ru"><fmt:message key="page.lang.russian"/></a>
     </li>