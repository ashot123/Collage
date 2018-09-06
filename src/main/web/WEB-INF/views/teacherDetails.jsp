<%@ page import="am.ak.coolage.collage.entities.CommonCourseDetails" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="teacherDetails" type="java.util.List<am.ak.coolage.collage.entities.CommonCourseDetails>" scope="request"/>

<html>
<head>
    <title>Teacher Details</title>

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
<H3>Teacher Details</H3>

<c:if test="${not empty teacherDetails}">
    <H4>Selected teacher is: ${param.teacherName} <%-- ${param.teacherId} --%>
        <%--<%=((CommonCourseDetails)((ArrayList)request.getAttribute("teacherDetails")).get(0)).getTeacherName() %>--%> </H4>
   <table style="margin-left:30px" border="1">
       <thead style="background: lightblue">
       <tr>
          <%-- <td>Teacher Name</td>--%>
           <td>Course Name</td>
           <td>Room Name</td>
       </tr>
       </thead>
       <c:forEach items="${teacherDetails}" var="currentTeacher">
           <tr>
               <%--<td>${currentTeacher.teacherName}</td>--%>
               <td>${currentTeacher.courseName}</td>
               <td>${currentTeacher.roomName}</td>

             <%-- <td>
                   <a href="/teacherDetails?userId=${currentTeacher.id}"
                      title="Details of ${currentTeacher.name}">
                           ${currentTeacher.name}
                   </a>
               </td>--%>
           </tr>
       </c:forEach>
   </table>
</c:if>

<c:import url="commonPagination.jsp">
    <%--<c:param name="pageNumber" value="2"/>--%>
    <%--<c:param name="rowCount" value="5"/>--%>
    <c:param name="numberOfPages" value="${numberOfPages}"/>
    <c:param name="servletName" value="/teacherDetails"/>

</c:import>
</body>
</html>
