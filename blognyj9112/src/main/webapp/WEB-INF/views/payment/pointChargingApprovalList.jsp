<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pointChargingApprovalList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">

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
				<h1>결제 승인 리스트</h1>
					<table class="table table-hober"  id="tableCss">
						<thead>
							<tr>
								<th>회원아이디</th>
								<th>회원명</th>
								<th>포인트결제</th>
								<th>포인트결제날짜</th>
								<th>승인담당자번호</th>
								<th>승인날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pointChargingApproval" items="${list}">
							<tr>
								<td>${pointChargingApproval.memberId }</td>
								<td>${pointChargingApproval.memberName }</td>
								<td>${pointChargingApproval.pointChargingSum }</td>
								<td>${pointChargingApproval.pointChargingDate }</td>
								<td>${pointChargingApproval.pointChargingDirectorNo }</td>
								<td>${pointChargingApproval.pointChargingApprovalDate }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div align="center">
						<nav>
							<ul class="pagination pagination-sm">
								<c:if test="${currentPage > 10}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/pointChargingApprovalList?currentPage=1">&laquo;</a>
									</li>
								</c:if>
								<c:if test="${firstBlockPage > 2}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/pointChargingApprovalList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage == i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage != i}">
									<li class="">
									</c:if>
										<a href="${pageContext.request.contextPath}/pointChargingApprovalList?currentPage=${i}">${i}</a>				
									</li>
								</c:forEach>
								<c:if test="${lastBlockPage < totalBlock}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/pointChargingApprovalList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
									</li>
								</c:if>
								<c:if test="${currentPage < lastPage}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/pointChargingApprovalList?currentPage=${lastPage}">&raquo;</a>
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