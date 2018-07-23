<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exerciseFeedResponseResultList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
function requestListBtn() {
    location.href="${pageContext.request.contextPath}/exerciseFeedbackList";
} 
function addRequestBtn() {
    location.href="${pageContext.request.contextPath}/exerciseFeedbackPtList";
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
			<h4>운동피드백답변리스트</h4>
				<table class="table table-hover" id="tableCss">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>아이디</th>
							<th>등록날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="exerciseFeedbackResult" items="${list}">
							<tr>
								<td>${exerciseFeedbackResult.exerciseFeedbackRequestNo }</td>
								<td><a id="purple" href="${pageContext.request.contextPath}/exerciseFeedResponseResultDetail?exerciseFeedbackRequestNo=${exerciseFeedbackResult.exerciseFeedbackRequestNo }">${exerciseFeedbackResult.exerciseFeedbackTitle }</a></td>
								<td>${exerciseFeedbackResult.memberId }</td>
								<td>${exerciseFeedbackResult.exerciseFeedbackResultDate }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div align="right">
					<button class="btn btn-primary" onclick="requestListBtn()">요청리스트</button>
					<c:if test="${sessionScope.memberSessionLevel != 4 }">
						<button class="btn btn-primary" onclick="addRequestBtn()">운동피드백 요청하기</button>
					</c:if>
				</div>
				<div align="center">
					<nav>
						<ul class="pagination pagination-sm">
							<c:if test="${currentPage > 10}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedResponseResultList?currentPage=1">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${firstBlockPage > 2}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedResponseResultLis?currentPage=${firstBlockPage-1}">&lsaquo;</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
								<c:if test="${currentPage == i}">
								<li class="active">
								</c:if>
								<c:if test="${currentPage != i}">
								<li class="">
								</c:if>
									<a href="${pageContext.request.contextPath}/exerciseFeedResponseResultLis?currentPage=${i}">${i}</a>				
								</li>
							</c:forEach>
							<c:if test="${lastBlockPage < totalBlock}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedResponseResultLis?currentPage=${lastBlockPage+1}">&rsaquo;</a>
								</li>
							</c:if>
							<c:if test="${currentPage < lastPage}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedResponseResultLis?currentPage=${lastPage}">&raquo;</a>
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
