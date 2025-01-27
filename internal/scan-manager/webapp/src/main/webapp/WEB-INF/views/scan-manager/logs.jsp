<!--
~ Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
~
~ WSO2 Inc. licenses this file to you under the Apache License,
~ Version 2.0 (the "License"); you may not use this file except
~ in compliance with the License.
~ You may obtain a copy of the License at
~
~ http://www.apache.org/licenses/LICENSE-2.0
~
~ Unless required by applicable law or agreed to in writing,
~ software distributed under the License is distributed on an
~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
~ KIND, either express or implied. See the License for the
~ specific language governing permissions and limitations
~ under the License.
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../fragments/header.html" %>
</head>
<body>
<%@ include file="../fragments/nav_bar.jsp" %>
<div class="container">
    <c:choose>
        <c:when test="${scanData != null}">
            <div class="row">
                <div class="page-header">
                    <h1>Logs</h1>
                    <h3><b>Scan: </b>${e:forHtml(scanData.name)}</h3>
                </div>
            </div>
            <hr>
            <div class="row">
                
                <c:if test="${logListResponse.logs.size() eq 0}">
                    <br/><br/>
                    <div style="float: left; padding: 5px;">
                        <h4>No Logs found</h4>
                    </div>
                </c:if>
            </div>
            <div class="row">
                <form action="/scan-manager/scans" method="get" class="scan-manager-page-nav-button"
                      style="padding-left: 0px">
                    <button class="btn btn-primary">Back to Scans</button>
                </form>
            </div>
            <c:choose>
                <c:when test="${logListResponse.logs.size() gt 0}">
                    <div class="row">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Timestamp</th>
                                <th scope="col">Type</th>
                                <th scope="col">Message</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach begin="0" end="${logListResponse.logs.size() - 1}" var="index">
                                <tr>
                                    <th scope="row">${logListResponse.logs.get(index).timeStamp}</th>
                                    <td>${logListResponse.logs.get(index).type.name()}</td>
                                    <td>${e:forHtml(logListResponse.logs.get(index).message)}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <c:if test="${!fn:contains(scanData.jobId, 'pre_job_id')}">
                        <div class="row">
                            <div class="col-md-6" style="padding-left: 0px">
                                <form action="/scan-manager/logs" method="get" class="scan-manager-page-nav-button">
                                    <input type="hidden" name="page" value="1"/>
                                    <input type="hidden" name="jobId" value="${scanData.jobId}"/>
                                    <c:choose>
                                        <c:when test="${!logListResponse.isFirstPage()}">
                                            <button class="btn btn-primary">First</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary" disabled>First</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <form action="/scan-manager/logs" method="get" class="scan-manager-page-nav-button">
                                    <input type="hidden" name="page" value="${logListResponse.currentPage - 1}"/>
                                    <input type="hidden" name="jobId" value="${scanData.jobId}"/>
                                    <c:choose>
                                        <c:when test="${!logListResponse.isFirstPage()}">
                                            <button class="btn btn-primary">Previous Page</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary" disabled>Previous Page</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <form action="/scan-manager/logs" method="get" class="scan-manager-page-nav-button">
                                    <input type="hidden" name="page" value="${logListResponse.currentPage + 1}"/>
                                    <input type="hidden" name="jobId" value="${scanData.jobId}"/>
                                    <c:choose>
                                        <c:when test="${!logListResponse.isLastPage()}">
                                            <button class="btn btn-primary">Next Page</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary" disabled>Next Page</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                                <form action="/scan-manager/logs" method="get" class="scan-manager-page-nav-button">
                                    <input type="hidden" name="page" value="${logListResponse.totalPages}"/>
                                    <input type="hidden" name="jobId" value="${scanData.jobId}"/>
                                    <c:choose>
                                        <c:when test="${!logListResponse.isLastPage()}">
                                            <button class="btn btn-primary">Last</button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary" disabled>Last</button>
                                        </c:otherwise>
                                    </c:choose>
                                </form>
                            </div>
                            <div class="col-md-6">
                                <p style="padding-top: 14px; text-align: right">Page ${logListResponse.currentPage} of
                                        ${logListResponse.totalPages}</p>
                            </div>
                        </div>
                    </c:if>
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="page-header">
                    <h1>Logs</h1>
                </div>
            </div>
            <hr>
            <div class="row" >
                <div class="col-md-12">
                    <c:choose>
                        <c:when test="${fn:contains(param.jobId, 'pre_job_id')}">
                            <h5>Provided scan cannot be found. This scan may have completed its preparation tasks and
                                moved
                                to the submission phase. Please check
                                the <a href="/scan-manager/scans">scans page</a> for the available scans.</h5>
                        </c:when>
                        <c:otherwise>
                            <h5>Provided scan cannot be found. Please check the <a href="/scan-manager/scans">scans
                                page</a>
                                for the available scans.</h5>
                            <br>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="../fragments/footer.jsp" %>
</body>
</html>
