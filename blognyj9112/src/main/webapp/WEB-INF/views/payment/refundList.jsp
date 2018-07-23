<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>refundList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
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
				<h4>환불 리스트</h4>
				<form id="formSearch" name="formSearch" onsubmit="return formSearchcheck()" action="${pageContext.request.contextPath}/refundListSearch" method="post">
					<select name="keyOption" size="1">
			            <option value="refundNo" <c:if test="${'refundNo'==keyOption }"> selected</c:if>>번호</option>
			            <option value="memberId" <c:if test="${'memberId'==keyOption }"> selected</c:if>>아이디</option>
			            <option value="refundDate" <c:if test="${'refundDate'==keyOption }"> selected</c:if>>환불신청날짜</option>
			        </select>
					<input type="text" id="keyWord" name="keyWord" value="${keyWord}"/>
					<button type="submit">검색</button>  
			    </form> 
			    <c:choose>
					<c:when test="${searchKeywordresult > 0 }">
						총 ${searchKeywordresult }개의 게시물을 찾았습니다.
						<span id="purple">${keyWord }</span> 검색한 리스트 결과입니다.
					</c:when>
					<c:when test="${searchKeywordresult eq 0 }">
						<span id="purple"> ${keyWord }</span>로 해당하는 리스트 검색 결과가 없습니다.
					</c:when>
				</c:choose>
				<table class="table table-hober" id="tableCss">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>아이디</th>
							<th>환불신청날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="refund" items="${list}">
						<tr>
							<td>${refund.refundNo }</td>
							<td><a id="purple" href="${pageContext.request.contextPath }/refundListDetail?refundNo=${refund.refundNo}">${refund.refundTitle }</a></td>
							<td>${refund.memberId }</td>
							<td>${refund.refundDate }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div align="center">
					<nav>
						<ul class="pagination pagination-sm">
							<c:if test="${currentPage > 10}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/refundList?currentPage=1">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${firstBlockPage > 2}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/refundList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage eq i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage ne i}">
									<li class="">
									</c:if>
									<a href="${pageContext.request.contextPath}/refundList?currentPage=${i}">${i}</a>				
								</li>
							</c:forEach>
							<c:if test="${lastBlockPage < totalBlock}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/refundList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
								</li>
							</c:if>
							<c:if test="${currentPage < lastPage}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/refundList?currentPage=${lastPage}">&raquo;</a>
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