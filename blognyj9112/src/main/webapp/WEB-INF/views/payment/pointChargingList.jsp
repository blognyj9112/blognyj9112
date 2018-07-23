<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pointChargingList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
function check() {		
	var pointChargingRoot = $("input[type=radio]:checked").val();
	if(pointChargingForm.pointChargingNumber.value == "") {
		alert("전화번호를 입력해주세요.");
		pointChargingForm.pointChargingNumber.focus();
		return false;
	}if(pointChargingRoot == "" || pointChargingRoot == null|| pointChargingRoot!= 3) {
		alert("결제방식을 선택해주세요.");
		pointChargingForm.selectedPointChargRoot.focus();
		return false;
		}else{
		alert("결제 신청이 완료 되었습니다.");
		return true;
	}
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
				<h4>결제 승인 대기 리스트</h4>
					<table class="table table-hober" id="tableCss">
						<thead>
							<tr>
								<th>회원아이디</th>
								<th>회원명</th>
								<th>포인트결제</th>
								<th>포인트결제날짜</th>
								<th>승인</th>
								<th>거절</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="pointCharging" items="${list}">
							<tr>
								<td>${pointCharging.memberId }</td>
								<td>${pointCharging.memberName }</td>
								<td>${pointCharging.pointChargingSum }</td>
								<td>${pointCharging.pointChargingDate }</td>
								<td><a id="purple" href="${pageContext.request.contextPath}/acceptCharging?pointChargingNo=${pointCharging.pointChargingNo}">승인</a></td>
								<td><a id="purple" href="${pageContext.request.contextPath}/deniedCharging?pointChargingNo=${pointCharging.pointChargingNo}">거절</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div align="center">
					<nav>
						<ul class="pagination pagination-sm">
							<c:if test="${currentPage > 10}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/pointChargingList?currentPage=1">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${firstBlockPage > 2}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/pointChargingList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
								<c:if test="${currentPage == i}">
									<li class="active">
								</c:if>
								<c:if test="${currentPage != i}">
									<li class="">
								</c:if>
									<a href="${pageContext.request.contextPath}/pointChargingList?currentPage=${i}">${i}</a>				
								</li>
							</c:forEach>
							<c:if test="${lastBlockPage < totalBlock}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/pointChargingList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
								</li>
							</c:if>
							<c:if test="${currentPage < lastPage}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/pointChargingList?currentPage=${lastPage}">&raquo;</a>
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