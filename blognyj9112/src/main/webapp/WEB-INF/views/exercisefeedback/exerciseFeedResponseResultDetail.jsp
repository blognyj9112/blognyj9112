<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exerciseFeedResponseResultDetail</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script>
function addEvaluationBtn() {
	var exerciseFeedbackEvaluationGrade =$("input[type=radio]:checked").val();
	console.log("exerciseFeedbackEvaluationGrade:"+exerciseFeedbackEvaluationGrade);
	var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
	if(confirm("평가를 등록하시겠습니까?")){
	    location.href="${pageContext.request.contextPath}/addexerciseFeedbackResponseResultevaluation?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo+"&&exerciseFeedbackEvaluationGrade="+exerciseFeedbackEvaluationGrade;
	}else {
		return false;
	}   
}
	function listBtn() {
		history.back();
	} 
	function addSanctionBtn() {
		 location.href="${pageContext.request.contextPath}/";
	} 
</script>
<style>
th{
text-align:center;
}
#purple{
color: #9c27b0;
font-weight: bold;
font-size : 14px;
}
#tableCss{
font-size : 14px;
}
h4{
text-align:center;
}
</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
			<input type="hidden" id="exerciseFeedbackRequestNo" value="${map.exerciseFeedbackResultDetail.exerciseFeedbackRequestNo}">
			<h5>exercuseFeedResponseResultDetail</h5>
				<div class="row">
					<div class="col-md-2">
					</div>
                     <div class="col-md-8">
					 	<div class="card">
					 		<div class="card-header" data-background-color="purple">
		                       <h4 class="title">그룹 상세보기</h4>
							</div>
							<div class="card-content">
							 	<div class="row">
									<div class="col-md-2">
									</div>
									<div class="col-md-8">
										<table class="table table-hober" id="tableCss">
											<tr>
												<th>요청번호</th>
												<td>${map.exerciseFeedbackResultDetail.exerciseFeedbackRequestNo }</td>
											<tr>
											<tr>
												<th>답변일</th>
												<td>${map.exerciseFeedbackResultDetail.exerciseFeedbackResultDate }</td>
											</tr>
											<tr>
												<th>아이디</th>
												<td>${map.exerciseFeedbackResultDetail.memberId }</td>
											<tr>
											<tr>
												<th>제목</th>
												<td>${map.exerciseFeedbackResultDetail.exerciseFeedbackTitle }</td>
											<tr>
											<tr>
												<th>내용</th>
												<td>${map.exerciseFeedbackResultDetail.exerciseFeedbackContent }</td>
											</tr>
										</table>
									</div>
									<div class='col-sm-5'>
										<p>피드백</p>
										<c:if test="${map.exerciseFeedbackResultCount > 0 }">
										<br><span>운동피드백</span><br>
											<c:forEach var="exerciseFeedback" items="${map.selectExerciseFeedback}">
												<span>운동명 : ${exerciseFeedback.exerciseName }</span>
												<span>/운동량 : ${exerciseFeedback.exerciseTime } (min)</span><br>
											</c:forEach>
										</c:if>
										<c:if test="${map.foodFeedbackResultCount > 0 }">
										<span>음식피드백</span><br>
											<c:forEach var="foodFeedback" items="${map.selectFoodFeedback}">
												<span>음식명 : ${foodFeedback.foodName }</span>
												<span>/음식량 : ${foodFeedback.ingestionAmount }(100g)</span><br>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
							<div class="row">
								<div class='col-sm-12'>
								<c:if test="${sessionScope.memberSessionLevel == 2 }">
									<c:if test="${map.result>0 }">
										평가한 날짜 : <span id="purple">${map.exerciseFeedbackEvaluation.exerciseFeedbackEvaluationDate }</span>
										평가점수 : <span id="purple">${map.exerciseFeedbackEvaluation.exerciseFeedbackEvaluationGrade }</span>
									</c:if>
									<c:if test="${map.result < 1 }">
										피드백을 평가해주세요!
										<form class="form-inline">
											<div class="form-group">
												1<input class="form-group" type="radio" name="gener" value="1">
											</div>
											<div class="form-group">
												2<input class="form-group" type="radio" name="gener" value="2">
											</div>
											<div class="form-group">
												3<input class="form-group" type="radio" name="gener" value="3">
											</div>
											<div class="form-group">
												4<input class="form-group" type="radio" name="gener" value="4">
											</div>
											<div class="form-group">
												5<input class="form-group" type="radio" name="gener" value="5">
											</div>
												<input class="btn btn-sm btn-primary pull-right" type="button" onclick="addEvaluationBtn()" value="평가하기">
										</form>
										<input class="btn btn-sm btn-primary" type="button" onclick="addSanctionBtn()" value="신고하기">
									</c:if>
								</c:if>
								</div>
							</div>
							<form class="form-inline">
								<c:if test="${map.countNext > 0 }">
									<div class="form-group">
										<p> <span id="purple">다음글 </span>: <a type="button" href="${pageContext.request.contextPath}/exerciseFeedResponseResultDetail?exerciseFeedbackRequestNo=${map.nextExerciseFeedback.exerciseFeedbackRequestNo}">${map.nextExerciseFeedback.exerciseFeedbackResultTitle }</a></p>
									</div>
								</c:if>
								<c:if test="${map.countNext eq 0 || map.countNext == null}">
									<div class="form-group">
										<p> <span id="purple">다음글</span>이 없습니다.</p>
									</div>
								</c:if>
								<c:if test="${map.countPrev >0 }">
									<div class="form-group pull-right">
										<p><span id="purple">이전글</span> : <a type="button" href="${pageContext.request.contextPath}/exerciseFeedResponseResultDetail?exerciseFeedbackRequestNo=${map.prevExerciseFeedback.exerciseFeedbackRequestNo}">${map.prevExerciseFeedback.exerciseFeedbackResultTitle }</a></p>
									</div>
								</c:if>
								<c:if test="${map.countPrev eq 0 || map.countPrev == null}">
									<div class="form-group pull-right">
										<p><span id="purple">이전글</span>이 없습니다.</p>
									</div>
								</c:if>
							</form>
							<div class="navbar-form navbar-right">
								<div class="form-group" style="margin:0px">
									<input type="button" class="btn btn-primary" onclick="listBtn()" value="목록으로">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>