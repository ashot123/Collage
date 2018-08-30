<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="students" type="java.util.List<am.ak.acoolage.collage.entities.Student>" scope="request"/>

<html>
<head>
    <title>Title</title>

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
<H3>Collage Students</H3>
<c:if test="${not empty students}">
    <table style="margin-left:30px" border="1">
        <thead>
        <tr>
            <td>ID</td>
            <td>First Name</td>
            <td>Last Name</td>
        </tr>
        </thead>
        <c:forEach items="${students}" var="currentStudent">
            <tr>
                <td>${currentStudent.id}</td>
                <td>${currentStudent.firstName}</td>
                <td>${currentStudent.lastName}</td>
                    <%--<td>${currentStudent.department}</td>--%>
            </tr>
        </c:forEach>

    </table>
</c:if>

<c:import url="commonPagination.jsp">
    <%--<c:param name="pageNumber" value="2"/>--%>
    <%--<c:param name="rowCount" value="5"/>--%>
    <c:param name="numberOfPages" value="${numberOfPages}"/>
    <c:param name="servletName" value="/students"/>

</c:import>
</body>
</html>
