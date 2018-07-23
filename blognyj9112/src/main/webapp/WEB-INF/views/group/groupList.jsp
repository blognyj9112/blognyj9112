<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
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
		location.href="${pageContext.request.contextPath}/groupListSearchSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	//1개월전 검색
	function SearchMonth(){
			today.setDate(today.getMonth() -1)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/groupListSearchSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	//6개월전 검색
	function SearchSixMonth(){
			today.setDate(today.getMonth() -6)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/groupListSearchSearch?startDate="+startDate+"&&endDate="+endDate; 
	}
	function groupListAll(){
		location.href="${pageContext.request.contextPath}/groupList";
	}
	function addGroupBtn(){
		location.href="${pageContext.request.contextPath}/addGroup";
	}
	function addGroupKindBtn(){
		location.href="${pageContext.request.contextPath}/addGroupKind";
	}
	function groupDeleteListBtn(){
		location.href="${pageContext.request.contextPath}/deleteGroupList";
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
				<h4>생성된 그룹 리스트</h4>
					<!-- 기간 검색 -->   	
					<div class="form-inline" style="float:left;">
						<form class="form-inline" id="formSearch" name="formSearch" onsubmit="return formSearchcheck()" action="${pageContext.request.contextPath}/groupListSearch" method="POST"> 
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
								<input type="button" class="btn btn-sm btn-primary" onclick="groupListAll()" value="전체보기">	
							</div>				
						</form>
					</div>
					<!-- 일반 검색 -->
					<div class="form-inline pull-right">
					 	<form class="form-inline" id="formSearchKeyword" name="formSearchKeyword" onsubmit="return formSearchKeywordcheck()" action="${pageContext.request.contextPath}/groupListKeywordSearch" method="post">
							<select class="form-control" name="keyOption" size="1">
					            <option value="all" <c:if test="${'all'==keyOption }"> selected</c:if>>전체검색</option>
					            <option value="groupName" <c:if test="${'groupName'==keyOption }"> selected</c:if>>그룹명</option>
					            <option value="groupKindName" <c:if test="${'groupKindName'==keyOption }"> selected</c:if>>그룹종류</option>
					        </select>
							<input class="form-control" type="text" id="keyWord" name="keyWord" value="${keyWord}"/>
							<div class="form-group">
								<button class="btn btn-white btn-round btn-just-icon" type="submit"><i class="material-icons">search</i></button>
							</div>
					    </form>
				    </div> 
				    <!-- 일반검색 결과 -->
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
						<!-- 기간별검색 결과 -->
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
					<table class="table table-hober" id="tableCss">
						<thead>
							<tr>
								<th>번호</th>
								<th>그룹명</th>
								<th>그룹종류</th>
								<th>날짜</th>
								<th>그룹상세보기</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="groupTable" items="${list}">
							<tr>
								<td>${groupTable.groupNo }</td>
								<td>${groupTable.groupName }</td>
								<td>${groupTable.groupKindName }</td>
								<td>${groupTable.groupCreateDate }</td>
								<td><a id="purple" href="${pageContext.request.contextPath}/groupDetail?groupNo=${groupTable.groupNo}">그룹상세보기</a></td>				
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="navbar-form navbar-left">
						<div  class="form-group" style="margin:0px">
							<input type="button" class="btn btn-primary" onclick="addGroupBtn()" value="그룹생성">
							<c:if test="${sessionScope.memberSessionLevel == 1 }">
							<input type="button" class="btn btn-primary" onclick="addGroupKindBtn()" value="그룹종류생성">
							</c:if>
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<div class="form-group" style="margin:0px">
							<input type="button" class="btn btn-primary" onclick="groupDeleteListBtn()" value="그룹삭제유예기간리스트">
						</div>
					</div>
					<div align="center">
						<nav>
							<ul class="pagination pagination-sm">
								<c:if test="${currentPage > 10}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupList?currentPage=1">&laquo;</a>
									</li>
								</c:if>
								<c:if test="${firstBlockPage > 2}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
									</li>
								</c:if>
									<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage == i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage != i}">
									<li class="">
									</c:if>
										<a href="${pageContext.request.contextPath}/groupList?currentPage=${i}">${i}</a>	
									</li>
								</c:forEach>	
								<c:if test="${lastBlockPage < totalBlock}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
									</li>
								</c:if>
								<c:if test="${currentPage < lastPage}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupList?currentPage=${lastPage}">&raquo;</a>
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