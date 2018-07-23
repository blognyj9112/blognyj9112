<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupKindList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
function addGroupBtn(){
	location.href="${pageContext.request.contextPath}/addGroupKind";
}
function groupDeleteListBtn(){
	location.href="${pageContext.request.contextPath}/groupMain";
}
</script>
<style>
th td{
text-align : center;
}
#purple{
color: #9c27b0;
font-weight: bold;
font-size : 14px;
}
#tableCss{
font-size : 14px;
}
</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
				<h4>그룹 종류 리스트</h4>   
					<table class="table table-hober" id="tableCss">
						<thead>
							<tr>
								<th>번호</th>
								<th>그룹종류</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="groupTable" items="${list}">
							<tr>
								<td>${groupTable.groupNo }</td>
								<td>${groupTable.groupKindName }</td>
								<td><a href="${pageContext.request.contextPath}/modifyGroupKind?groupKindName=${groupTable.groupKindName}">수정</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="navbar-form navbar-left">
						<div  class="form-group" style="margin:0px">
							<input type="button" class="btn btn-primary" onclick="addGroupKindBtn()" value="그룹종류등록">
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<div class="form-group" style="margin:0px">
							<input type="button" class="btn btn-primary" onclick="groupMainBtn()" value="그룹메인">
						</div>
					</div>
					<div align="center">
						<nav>
							<ul class="pagination pagination-sm">
								<c:if test="${currentPage > 10}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupKindList?currentPage=1">&laquo;</a>
									</li>
								</c:if>
								<c:if test="${firstBlockPage > 2}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupKindList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
									</li>
								</c:if>
									<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage == i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage != i}">
									<li class="">
									</c:if>
										<a href="${pageContext.request.contextPath}/groupKindList?currentPage=${i}">${i}</a>	
									</li>
								</c:forEach>	
								<c:if test="${lastBlockPage < totalBlock}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupKindList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
									</li>
								</c:if>
								<c:if test="${currentPage < lastPage}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupKindList?currentPage=${lastPage}">&raquo;</a>
									</li>
								</c:if>
							</ul>
						</nav>
					</div>
					</div>
					</div>
					</div>
</body>
</html>