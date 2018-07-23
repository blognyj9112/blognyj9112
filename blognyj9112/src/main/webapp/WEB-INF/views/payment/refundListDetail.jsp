<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>refundListDetail</title>
<jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
			<h2>환불정보상세보기</h2>
				<table>
					<tr>
						<td>No</td>
						<td>${map.refundDatail.refundNo }</td>
					<tr>
					<tr>
						<td>회원아이디</td>
						<td>${map.refundDatail.memberId }</td>
					<tr>
					<tr>
						<td>회원명</td>
						<td>${map.refundDatail.memberName }</td>
					<tr>
					<tr>
						<td>환불제목</td>
						<td>${map.refundDatail.refundTitle }</td>
					<tr>
					<tr>
						<td>환불금액</td>
						<td>${map.refundDatail.refundSum }</td>
					<tr>
					<tr>
						<td>환불사유</td>
						<td>${map.refundDatail.refundContent }</td>
					<tr>
					<tr>
						<td>신청일</td>
						<td>${map.refundDatail.refundDate }</td>
					<tr>
				</table>
			
				<div>
					<a href="${pageContext.request.contextPath}/refundList">리스트로 돌아가기</a>
					<a href="${pageContext.request.contextPath}/refundAccept?refundNo=${map.groupDetail.refundNo }">승인</a>
					<a href="${pageContext.request.contextPath}/refundDenied?refundNo=${map.groupDetail.refundNo }">거절</a>
				</div>
				<div>
					<c:if test="${map.countNext > 0 }">
						<p>다음글 : <a type="button" href="${pageContext.request.contextPath}/refundListDetail?refundNo=${map.nextGroup.refundNo}">${map.nextGroup.refundNo }</a></p>
					</c:if>
					<c:if test="${map.countNext eq 0 }">
						<p>다음글이 없습니다.</p>
					</c:if>
					<c:if test="${map.countPrev > 0 }">
						<p>이전글 : <a type="button" href="${pageContext.request.contextPath}/refundListDetail?refundNo=${map.prevGroup.refundNo}">${map.prevGroup.refundNo }</a></p>
					</c:if>
					<c:if test="${map.countPrev eq 0 }">
						<p>이전글이 없습니다.</p>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>