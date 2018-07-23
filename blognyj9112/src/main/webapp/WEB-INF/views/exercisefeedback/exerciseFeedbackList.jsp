<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exerciseFeedbackList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
	//날짜 형식 
	Date.prototype.yyyymmdd = function(){
	 var yyyy = this.getFullYear().toString();
	    var mm = (this.getMonth() + 1).toString();
	    var dd = this.getDate().toString();
	    return yyyy +"-"+(mm[1] ? mm : '0'+mm[0])+"-"+(dd[1] ? dd : '0'+dd[0]);
	}
	var today = new Date()

$(document).ready(function(){
	//검색 달력 생성
	var rangeDate = 31; // set limit day
	var setSdate, setEdate;
	  $.datepicker.setDefaults({
		    dateFormat: 'yy-mm-dd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		    showMonthAfterYear: true,
		    yearSuffix: '년'
		  });

		  $(function() {
		    $("#startDate, #endDate").datepicker();
		  });
	});
	//기간별 검색
	function formSearchcheck(){
   		var startDate = $('#startDate').val();
   		var endDate = $('#endDate').val();
   		console.log("startDate:"+startDate);
   		console.log("endDate:"+endDate);
 		if(startDate == ''|| startDate == null){
			alert('날짜를 입력해주세요.');
			formSearch.startDate.focus();
			return false;
		}else if(endDate == ''|| endDate == null){
			alert('날짜를 입력해주세요.');
			formSearch.endDate.focus();
			return false;
		}else{ 
			alert(startDate+'~'+endDate+'기간의 리스트 검색이 완료되었습니다.');
			return true;
		}
	}  
	//1주일전 검색
	function SearchWeek(){
		today.setDate(today.getDate() -7)
		var startDate = today.yyyymmdd()
		var endDate = (new Date).yyyymmdd()
		console.log("endDate:"+endDate);
		console.log("startDate:"+startDate);
		location.href="${pageContext.request.contextPath}/exerciseFeedbackListSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	//1개월전 검색
	function SearchMonth(){
			today.setDate(today.getMonth() -1)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/exerciseFeedbackListSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	//6개월전 검색
	function SearchSixMonth(){
			today.setDate(today.getMonth() -6)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/exerciseFeedbackListSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	
	//운동피드백등록
	function addExerciseFeedback(){
		location.href="${pageContext.request.contextPath}/exerciseFeedbackPtList";
	}
	//일반검색
	function formSearchKeywordcheck(){
   		var keyWord = $('#keyWord').val();
 		if(keyWord == ''|| keyWord == null){
			alert('검색하실 내용을 입력해주세요.');
			formSearchKeyword.keyWord.focus();
			return false;
		}else{ 
			alert('리스트 검색이 완료되었습니다.');
			return true;
		}
	} 
	function exerciseFeedbackAll(){
		location.href="${pageContext.request.contextPath}/exerciseFeedResponseResultList";
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
			<h4>요청한운동피드백리스트</h4>
				<!-- 기간 검색 -->   		
					<div class="form-inline" style="float:left;">
						<form class="form-inline" id="formSearch" name="formSearch" onsubmit="return formSearchcheck()" action="${pageContext.request.contextPath}/exerciseFeedbackListSearch" method="POST"> 
							<div class="form-group"> 
								<input type="text" class="form-control" id="startDate" name="startDate" placeholder="시작일">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="endDate" name="endDate" placeholder="종료일">
							</div>
							<div class="form-group">
								<button class="btn btn-white btn-round btn-just-icon" type="submit"><i class="material-icons">search</i></button>
							</div>
							<div class="form-group">
								<input type="button" class="btn btn-sm btn-primary" onclick="SearchWeek()" value="1주일">
							</div>
							<div class="form-group">
								<input type="button" class="btn btn-sm btn-primary" onclick="SearchMonth()" value="1개월">
							</div>
							<div class="form-group">
								<input type="button" class="btn btn-sm btn-primary" onclick="SearchSixMonth()" value="6개월">	
							</div>
							<div class="form-group">
								<input type="button" class="btn btn-sm btn-primary" onclick="exerciseFeedbackAll()" value="전체보기">	
							</div>				
						</form>
					</div>
				<!-- 일반 검색 -->
					<div class="form-inline pull-right">
						<form class="form-inline" id="formSearchKeyword" name="formSearchKeyword" onsubmit="return formSearchKeywordcheck()" action="${pageContext.request.contextPath}/exerciseFeedbackRequestListSearch" method="POST"> 
							<div class="form-group">
								 <select name="keyOption" class="form-control" size="1">
								     <option value="exerciseFeedbackRequestNo" <c:out value="${keyOption == 'exerciseFeedbackRequestNo'?'selected':''}"/>>번호</option>
							         <option value="exerciseFeedbackRequestTitle" <c:out value="${keyOption == 'exerciseFeedbackRequestTitle'?'selected':''}"/>>제목</option>
									 <c:if test="${sessionScope.memberSessionLevel == 1 }">
							            <option value="memberNo" <c:out value="${keyOption == 'memberNo'?'selected':''}"/>>일반회원번호</option>
							            <option value="teacherNo" <c:out value="${keyOption == 'teacherNo'?'selected':''}"/>>강사번호</option>
							        </c:if>
							         <c:if test="${sessionScope.memberSessionLevel == 2 }">
							      	  	<option value="memberName" <c:out value="${keyOption == 'memberName'?'selected':''}"/>>강사명</option>
							        </c:if>
							         <c:if test="${sessionScope.memberSessionLevel == 3 }">
							         	<option value="memberName" <c:out value="${keyOption == 'memberName'?'selected':''}"/>>강사명</option>
							        </c:if>
							         <c:if test="${sessionScope.memberSessionLevel == 4 }">
							            <option value="memberId" <c:out value="${keyOption == 'memberId'?'selected':''}"/>>회원아이디</option>
							        </c:if>
						        </select>
						    </div>
						    <div class="form-group">
								<input class="form-control" type="text" id="keyWord" name="keyWord" placeholder="검색어" value="${keyWord}"/>
							</div>
							<div class="form-group">
								<button class="btn btn-white btn-round btn-just-icon" type="submit"><i class="material-icons">search</i></button>
							</div>
						</form>
					</div>
				<div>
				<!-- 일반검색 -->
				<form>
				<c:choose>
					<c:when test="${searchKeywordresult > 0 }">
						총 ${searchKeywordresult }개의 게시물을 찾았습니다.
						<span id="purple">${keyWord }</span> 검색한 리스트 결과입니다.
					</c:when>
					<c:when test="${searchKeywordresult eq 0 }">
						<span id="purple"> ${keyWord }</span>로 해당하는 리스트 검색 결과가 없습니다.
					</c:when>
				</c:choose>
				<!-- 기간별검색 -->
				<c:choose>
					<c:when test="${searchresult > 0 }">
						총 ${searchresult }개의 게시물을 찾았습니다.
						<span id="purple">${startDate }</span> ~ <span id="purple">${endDate }</span>기간 동안의 리스트 검색 결과입니다.
					</c:when>
					<c:when test="${searchresult eq 0 }">
						<span id="purple">${startDate }</span> ~ <span id="purple">${endDate }</span> 기간 동안의 해당하는 리스트 검색 결과가 없습니다.
					</c:when>
				</c:choose>
				</form>
				<table class="table table-hover" id="tableCss">
					<thead>
						<tr>
							<th>피드백번호</th>
							<th>제목</th>
							<c:if test="${sessionScope.memberSessionLevel == 4 }">
								<th>요청한회원아이디</th>
								<th>요청날짜</th>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 1 }">
								<th>요청한회원번호</th>
								<th>강사번호</th>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 2 }">
								<th>강사명</th>
								<th>요청날짜</th>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 3 }">
								<th>강사명</th>
								<th>요청날짜</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="exerciseFeedback" items="${list}">
						<tr>
							<td>${exerciseFeedback.exerciseFeedbackRequestNo }</td>
							<td><a id="purple" href="${pageContext.request.contextPath}/exerciseFeedbackRequestDetail?exerciseFeedbackRequestNo=${exerciseFeedback.exerciseFeedbackRequestNo }">${exerciseFeedback.exerciseFeedbackRequestTitle }</a></td>
							<c:if test="${sessionScope.memberSessionLevel == 4 }">
								<td>${exerciseFeedback.memberId }</td>
								<td>${exerciseFeedback.exerciseFeedbackRequestDate }</td>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 1 }">
								<td>${exerciseFeedback.memberNo }</td>
								<td>${exerciseFeedback.teacherNo }</td>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 2 }">
								<td>${exerciseFeedback.memberName }</td>
								<td>${exerciseFeedback.exerciseFeedbackRequestDate }</td>
							</c:if>
							<c:if test="${sessionScope.memberSessionLevel == 3 }">
								<td>${exerciseFeedback.memberName }</td>
								<td>${exerciseFeedback.exerciseFeedbackRequestDate }</td>
							</c:if>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="navbar-form navbar-left">
					<div  class="form-group" style="margin:0px">
						<input type="button" class="btn btn-primary" onclick="addExerciseFeedback()" value="운동피드백요청등록">
					</div>
				</div>
				<div class="navbar-form navbar-right">
					<div class="form-group" style="margin:0px">
						<input type="button" class="btn btn-primary" onclick="exerciseFeedbackApprovalResult()" value="운동피드백요청결과">
					</div>
				</div>
				<div align="center">
					<nav>
						<ul class="pagination pagination-sm">
							<c:if test="${currentPage > 10}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedbackList?currentPage=1">&laquo;</a>
								</li>
							</c:if>
							<c:if test="${firstBlockPage > 2}">
								<li>
									<a aria-label="first" href="${pageContext.request.contextPath }/exerciseFeedbackList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
								</li>
							</c:if>
							<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
								<c:if test="${currentPage == i}">
								<li class="active">
								</c:if>
								<c:if test="${currentPage != i}">
								<li class="">
								</c:if>
									<a href="${pageContext.request.contextPath}/exerciseFeedbackList?currentPage=${i}">${i}</a>				
								</li>
							</c:forEach>	
							<c:if test="${lastBlockPage < totalBlock}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedbackList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
								</li>
							</c:if>
							<c:if test="${currentPage < lastPage}">
								<li>
									<a aria-label="last" href="${pageContext.request.contextPath}/exerciseFeedbackList?currentPage=${lastPage}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</nav>
				</div>
	 		</div>
		</div>
		</div>
	</div>
</body>
</html>