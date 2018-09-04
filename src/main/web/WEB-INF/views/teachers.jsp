<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="teachers" type="java.util.List<am.ak.coolage.collage.entities.Teacher>" scope="request"/>

<html>
<head>
    <title>Teachers</title>

    <style>
        body {
            background: #dbe7f3;
            text-align: left;
            width: 100%;
            height: 100%;
            margin: 0;
            text-indent: 30px;
        }

    </style>
</head>
<body>
<%--<img src="<c:url value="/img/pageUS.jpg"/>">--%>
<br/>
<H3>Collage Teachers</H3>
<c:if test="${not empty teachers}">
    <table style="margin-left:30px" border="1">
        <thead style="background: lightblue">
        <tr>
            <td>ID</td>
            <td>Name</td>
        </tr>
        </thead>
        <c:forEach items="${teachers}" var="currentTeacher">
            <tr>
                <td>${currentTeacher.id}</td>
                <td>${currentTeacher.name}</td>
                    <%--<td>${currentStudent.department}</td>--%>
            </tr>
        </c:forEach>

    </table>
</c:if>

<c:import url="commonPagination.jsp">
    <%--<c:param name="pageNumber" value="2"/>--%>
    <%--<c:param name="rowCount" value="5"/>--%>
    <c:param name="numberOfPages" value="${numberOfPages}"/>
    <c:param name="servletName" value="/teachers"/>

</c:import>
</body>
</html>
