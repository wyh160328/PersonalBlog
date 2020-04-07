<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>流程查找</title>
		<!-- procedure/myProcedureList.jsp -->
		<link href="${pageContext.request.contextPath}/jspFile/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<script>
			function checkData() {
					if(document.getElementById("ptitle").value === ""){
						alert("请输入关键字");
						return false;
					}
					if(document.getElementById("pkind").value ==="0"){
						alert("请选择查找类型");
						return false;
					}
			}
		</script>
	</head>

	<body>

		<div class="container-fluid">
			<div class="panel panel-primary">
				<!-- Default panel contents -->
				<div class="panel-heading">流程查找</div>
                <div class="panel-body">
                    <h3 align="center">流程查找</h3>
                    <form action="#" method="post"  class="form-horizontal" onsubmit="return checkData();">
                        <input type="hidden" id="mUserKind" value="${userKindNumber }" />
                        <div class="form-group">
                            <label for="ptitle" class="col-sm-2 control-label">关键词</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="ptitle" name="ptitle" placeholder="填写搜索的关键词" value="${ptitle != null ? ptitle :''}">
                            </div>
                        </div>
                        <!--查询类型-->
                        <div class="form-group">
                            <label for="pkind" class="col-sm-2 control-label">查找类型</label>
                            <div class="col-sm-10 ">
                                <select id="pkind" name="pkind">
                                    <option value="0" ${pkind != null ? "" :"selected"}>——选择类型——</option>
                                    <option value="pid" ${pkind == "pid" ? "selected" :""}>通过编号查找</option>
                                    <option value="pname" ${pkind == "pname"  ? "selected" :""}>通过名称查找</option>
                                    <option value="project" ${pkind == "project" ? "selected" :""}>通过项目查找</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input value="查询" type="submit" class="btn btn-success" />
                                <input value="重置" type="reset" class="btn btn-default" />
                            </div>
                        </div>
                    </form>
                </div>
                <%--                数据显示--%>
                <div id="datashow" style="display: ${showdata}">
                <!-- Table -->
				<table class="table table-hover table-bordered">
					<tr>
						<th>序号</th>
						<th>流程编号</th>
						<th>流程名称</th>
						<th>项目名称</th>
						<th>创建时间</th>
						<th>创建人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${mplProcedureList }" var="map" varStatus="status">
						<tr>
							<td>${status.index+1+(currentPage-1)*5 }</td>
							<td>${map.get('id') }</td>
							<td>${map.get('name') }</td>
							<td>${map.get('produceName') }</td>
							<td>${map.get('createDate') }</td>
							<td>${map.get('createPerson') }</td>
							<td>
								<c:choose>
									<c:when test="${map.get('status')==1 }">
										<span class="label label-success">通过</span>
									</c:when>
									<c:when test="${map.get('status')==2 }">
										<span class="label label-default">未通过</span>
									</c:when>
									<c:when test="${map.get('status')==3 }">
										<span class="label label-warning">进行中</span>
									</c:when>
									<c:when test="${map.get('status')==4 }">
										<span class="label label-danger">错误</span>
									</c:when>
									<c:otherwise>
										<span class="label label-info">异常</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td><a href="${pageContext.request.contextPath}/procedure/mySubmitDetail.do?submitId=${map.get('id') }" class="btn btn-info" type="button">查看</a></td>
							<!-- 查看后，到procedure/myProcedureDetail.jsp -->
						</tr>
					</c:forEach>
					
				</table>
				<!--底部开始-->
				<div class="panel-footer">

					<div class="pull-" align="center">
						<ul class="pagination">
							<li class="${currentPage==1 ? 'disabled':'' }">
								<c:if test="${currentPage!=1 }">
									<a href="${pageContext.request.contextPath}/procedure/myList.do?page=${currentPage-1 }" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</c:if>
								<c:if test="${currentPage==1 }">
									<a aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</c:if>
							</li>
							<c:forEach var="pageNumber" begin="1" end="${allPage }">
								<li class="${pageNumber==currentPage ? 'active':'' }">
									<a href="${pageContext.request.contextPath}/procedure/myList.do?page=${pageNumber }">${pageNumber } </a>
								</li>
							</c:forEach>
							<li class="${currentPage==allPage ? 'disabled':'' }">
								<c:if test="${currentPage!=allPage }">
									<a href="${pageContext.request.contextPath}/procedure/myList.do?page=${currentPage+1 }" aria-label="Previous">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</c:if>
								<c:if test="${currentPage==allPage }">
									<a aria-label="Previous">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</c:if>
							</li>
						</ul>
					</div>
				</div>
				<!--底部结束-->
                </div>
            <%--数据显示结束--%>
			</div>

		</div>

		<script src="${pageContext.request.contextPath}/jspFile/js/jquery-1.12.4.min.js"></script>
		<script src="${pageContext.request.contextPath}/jspFile/bootstrap/js/bootstrap.min.js"></script>
	</body>

</html>