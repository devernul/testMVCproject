<%@ include file="header.jsp" %>
<spring:htmlEscape defaultHtmlEscape="true" />

<table>
    <tr>
        <th>
            <h1><spring:message code="TaskList"/> (${statusName.toString()})</h1>
            <br>
            <c:if test="${not empty countsTasks}">
                <h2 class="pdfExp"><a href="/downloadPDF" target="_blank"><spring:message code="ExportPDF"/></a></h2>
            </c:if>
        </th>
        <th>
            <c:if test="${not empty task.name}">
                 <h1><spring:message code="FormEdit"/> <h1>
            </c:if>
            <c:if test="${empty task.name}">
                <h1><spring:message code="FormAdd"/> <h1>
            </c:if>
        </th>
    </tr>>
    <tr>
    <td style="width: 55%;">
            <table border="1" class="listTable">
                <tr>
                    <td><spring:message code="Name"/></td>
                    <td><spring:message code="Date"/></td>
                    <td><spring:message code="Description"/></td>
                    <td><spring:message code="Status"/></td>
                    <td><spring:message code="Actions"/></td>
                    <td><spring:message code="StatusChange"/></td>
                </tr>
                <c:forEach items="${tasks}" var="t">
                    <tr>
                        <td><c:out value="${t.name}"/></td>
                        <td><c:out value="${t.date}"/></td>
                        <td><c:out value="${t.description}"/></td>
                        <td><c:out value="${t.status.name}"/></td>
                        <td><a class ="changeIt" href="/tasks/edit/${t.taskId}"><spring:message code="Edit"/></a>&nbsp;<a class="deleteIt" href="/tasks/delete/${t.taskId}"><spring:message code="Delete"/></a></td>

                            <c:if test="${t.status.name.equals('New')}">
                                <td class="beginHref">
                                        <a href="/tasks/begin/${t.taskId}"><spring:message code="Begin"/></a>&nbsp;
                                </td>
                            </c:if>
                            <c:if test="${t.status.name.equals('Process')}">
                                <td class="endHref">
                                        <a href="/tasks/complete/${t.taskId}"><spring:message code="Complete"/></a>
                                </td>
                            </c:if>

                    </tr>
                </c:forEach>
                    <c:if test="${countsTasks>5}">
                        <tr>

                            <td>
                                <c:if test="${page_id>1}">
                                    <span><a href="/tasks/page_id/${page_id-1}/"><h1>${page_id-1}</h1></a></span>
                                </c:if>
                            </td>
                            <td><c:if test="${page_id>1}"><-- <spring:message code="LastPage"/></c:if></td>



                            <td> <c:if test="${page_id>0}"><spring:message code="CurrentPage"/>:</c:if></td>
                            <td>
                                <c:if test="${page_id>0}">
                                    <span><a href="/tasks/page_id/${page_id}/"><h1>${page_id}</h1></a></span>
                                </c:if>
                            </td>



                            <td><c:if test="${tasks.size()==5}"><spring:message code="NextPage"/> --></c:if></td>
                            <td>
                                <c:if test="${tasks.size()==5}">
                                    <span><a href="/tasks/page_id/${page_id+1}/"><h1>${page_id+1}</h1></a></span>
                                </c:if>
                            </td>

                        </tr>
                        <tr>
                            <td colspan="6">
                                <div class="simplePagination">
<%--                                    <c:if test="${not empty page_id}">
                                        <span ><a href="#"><b> ${page_id} </b></a></span>
                                    </c:if>
                                    <c:if test="${page_id>0}">
                                        <span><a href="/tasks/page_id/${page_id-1}/"><b><<</b></a></span>
                                    </c:if>

                                    <c:if test="${tasks.size()==5}">
                                        <span><a href="/tasks/page_id/${page_id+1}/"><b>>></b></a></span>
                                    </c:if>--%>
                                    <b><spring:message code="KolVoTasks"/>: ${countsTasks}</b>
                                </div>
                            </td>
                        </tr>
                    </c:if>
            </table>
        </td>
        <td>
            <form:form commandName="task" method="POST" id="formCreateUpdateTask">
                <form:hidden path="taskId"/>

                <table class="formTable">
                    <tr>
                        <td><spring:message code="Name"/>:</td>
                        <td><form:input path="name"/></td>
                        <td class="noBorder"><form:errors path="name"/></td>

                    </tr>

                    <tr>
                        <td><spring:message code="Date"/>:</td>
                        <td><form:input path="date" placeholder="MM/dd/yyyy"/></td>
                        <td class="noBorder"><form:errors path="date"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="Description"/>:</td>
                        <td><form:textarea path="description" cols="40" rows="10"/></td>
                        <td class="noBorder"><form:errors path="description"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="Status"/>:</td>
                        <td><form:select path="status" items="${statuses}" itemLabel="name" itemValue="statusId"/></td>
                        <td class="noBorder"><form:errors path="status"/></td>
                    </tr>
                    <tr>

                        <td align="right" colspan="3"><input type="submit" class="buttonSteklo" value='<spring:message code="Submit"/>'></td>

                    </tr>


                </table>

            </form:form>
        </td>
    </tr>

</table>