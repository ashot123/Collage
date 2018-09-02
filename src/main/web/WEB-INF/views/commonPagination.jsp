<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="numberOfPages" value="${param.numberOfPages}"/> <%--//4--%>
<c:set var="servletName" value="${param.servletName}"/> <%--//4--%>
<br/>
<%--<c:if test="${page gt numberOfPages}">
    <c:set  var="page"  value="1"/>
</c:if>--%>
<c:choose>
    <c:when test="${numberOfPages eq 0}">
        <div>
            <c:out value="Table is empty"/>
        </div>
    </c:when>
    <c:otherwise>
        <table border="0" cellpadding="0" cellspacing="0" style="margin-left:30px">
            <tr>
                <td>
                        <%--For displaying Previous link --%>
                    <c:if test="${page gt 1}">
                        <a href="${servletName}?page=${page - 1}">Previous</a>
                    </c:if>
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${i!=page}">
                                <%--<c:set var="temp1" value="${i}"/>
                                <a href='/students?page1=<c:out value="${temp1}"/>'></a>--%>
                                <a href="${servletName}?page=${i}">${i}&nbsp;</a>
                            </c:when>
                            <c:otherwise>
                                <c:out value="${i}"/>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        <%--For displaying Next link --%>
                    <c:if test="${page lt numberOfPages}">
                        <a href="${servletName}?page=${page + 1}">Next</a>
                    </c:if>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>

