<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="${pageContext.request.contextPath}/resources/theme1/css/realProject.css" rel="stylesheet" >

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>

<title><spring:message code="ToDoList"/></title>
<table >
    <tr>
        <td align="right" colspan="4">
            <h1 class="choseLang"><span><spring:message code="ChoseLang"/>:</span>
                <a href="?locale=en">en</a> / <a href="?locale=ru">ru</a>
            </h1>
        </td>
    </tr>
    <tr>
        <td align="center" colspan="4"><h1><spring:message code="FilterbyStatus"/> </h1></td>
    </tr>
    <tr>
    <c:forEach items="${statuses}" var="st">
            <td align="center"><h2><a href="/tasks/filter/${st.statusId}/">${st.name}</a></h2></td>
     </c:forEach>
    <td align="center"><h2><a href="/tasks"><spring:message code="All"/></a></h2></td>
    </tr>
</table>