<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exerciseFeedbackPtList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
	// 검색
	function formSearchcheck(){
   		var keyWord = $('#keyWord').val();
 		if(keyWord == ''|| keyWord == null){
			alert('검색하실 내용을 입력해주세요.');
			formSearch.keyWord.focus();
			return false;
		}else{ 
			alert('리스트 검색이 완료되었습니다.');
			return true;
		}
	}  
	function requestListBtn(){
		location.href="${pageContext.request.contextPath}/exerciseFeedbackList";
	}
	function requestListBtn(){
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
			<h4>운동 피드백 요청 스텝1</h4>
				피드백을 요청할 강사를 선택해주세요
				<p id="purple">회원님의 잔액이 1,000포인트 이상 있어야 진행할 수 있습니다.</p>
				<!-- 일반검색 -->
					<div align="right">
						<form class="form-inline" id="formSearch" name="formSearch" onsubmit="return formSearchcheck()" action="${pageContext.request.contextPath}/exerciseFeedbackPtListSearch" method="POST"> 
							<div class="form-group">
								<select name="keyOption" class="form-control" size="1">
								    <option value="memberName" <c:out value="${keyOption == 'memberName'?'selected':''}"/>>이름</option>
								    <option value="memberId" <c:out value="${keyOption == 'memberId'?'selected':''}"/>>아이디</option>
								    <option value="evaluationAverageGrade" <c:out value="${map.keyOption == 'evaluationAverageGrade'?'selected':''}"/>>평가점수</option>
								</select>
						    </div>
						    <div class="form-group">
								<input type="text" class="form-control" id="keyWord" name="keyWord" value="${keyWord}"/>
							</div>
							<div class="form-group">
								<button class="btn btn-white btn-round btn-just-icon" type="submit"><i class="material-icons">search</i></button>
							</div>
							<div class="form-group">
								<input class="btn btn-sm btn-primary" type="button" onclick="listBtn()" value="전체보기">
							</div>					
						</form>
					</div>
				<div>
				<!-- 일반검색 결과 -->
				<c:choose>
					<c:when test="${searchKeywordresult > 0 }">
						총 ${searchKeywordresult }개의 게시물을 찾았습니다.
						<span id="purple">${keyWord }</span> 검색한 리스트 결과입니다.
					</c:when>
					<c:when test="${searchKeywordresult eq 0 }">
						
						 <span id="purple">${keyWord }</span>로 해당하는 리스트 검색 결과가 없습니다.
					</c:when>
				</c:choose>
				</div>
				<table class="table table-hover" id="tableCss">
					<thead>
						<tr>
							<th>강사아이디</th>
							<th>강사명</th>
							<th>근무지</th>
							<th>총피드백수</th>
							<th>피드백동의수</th>
							<th>평가점수</th>
							<th>선택</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="teacher" items="${exercisePtlist}">
							<tr>
								<td>${teacher.memberId }</td>
								<td>${teacher.memberName }</td>
								<td>${teacher.memberWorkSpace }</td>
								<td>${teacher.memberTotalFeedback }</td>
								<td>${teacher.memberAgreeFeedback }</td>
								<td>${teacher.evaluationAverageGrade }</td>
								<td><a id="purple" href="${pageContext.request.contextPath}/exerciseFeedbackRequest?memberNo=${teacher.memberNo}">선택</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div align="right">
					<button class="btn btn-primary" onclick="requestListBtn()">목록으로</button>
				</div>
				<div align="center">
					<nav>
						<ul class="pagination pagination-sm">
							<c:if test="${currentPage > 10}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedbackPtList?currentPage=1">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${firstBlockPage > 2}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedbackPtList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
								</li>
							</c:if>
								<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage == i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage != i}">
									<li class="">
									</c:if>
										<a href="${pageContext.request.contextPath}/exerciseFeedbackPtList?currentPage=${i}">${i}</a>				
									</li>
								</c:forEach>	
							<c:if test="${lastBlockPage < totalBlock}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedbackPtList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
								</li>
							</c:if>
							<c:if test="${currentPage < lastPage}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedbackPtList?currentPage=${lastPage}">&raquo;</a>
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