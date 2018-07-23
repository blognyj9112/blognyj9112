<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupMemberList</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
var groupNo = $('#groupNo').val();
var groupName = $('#groupName').val();
console.log("groupNo:"+groupNo);
	function inviteMemberBtn() {
		location.href="${pageContext.request.contextPath}/inviteMemberForm?groupNo="+groupNo;
	}   
	//체크박스 전체선택
	function checkAll(){
	    if( $('#selectAll').is(':checked') ){
	      $('input:checkbox[name=groupMemberCheck]').prop('checked', true);
	    }else{
	      $('input:checkbox[name=groupMemberCheck]').prop('checked', false);
	    }
	}
	//선택해서 삭제하기
	function outGroupMember(){
	  $('input:checkbox[name="groupMemberCheck"]:checked').each(function() {
		var memberNo = $(this).val();
		console.log('memberNo:'+memberNo);
		if(memberNo == ''){
			    alert('추방할 회원을 선택하세요.');
			    return false;
		}else{
			if(confirm('추방하시겠습니까?')){
				alert('회원이 추방 되었습니다.');
				location.href="${pageContext.request.contextPath}/outGroupMember?memberNo="+memberNo;
			}
		}	
	  });
	}
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
		location.href="${pageContext.request.contextPath}/inviteGroupListSearchSearch?startDate="+startDate+"&&endDate="+endDate+"&&groupName="+groupName; 
	}
	//1개월전 검색
	function SearchMonth(){
			today.setDate(today.getMonth() -1)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/inviteGroupListSearchSearch?startDate="+startDate+"&&endDate="+endDate+"&&groupName="+groupName; 
	}
	//6개월전 검색
	function SearchSixMonth(){
			today.setDate(today.getMonth() -6)
			var startDate = today.yyyymmdd()
			var endDate = (new Date).yyyymmdd()
			console.log("endDate:"+endDate);
			console.log("startDate:"+startDate);
			location.href="${pageContext.request.contextPath}/inviteGroupListSearchSearch?startDate="+startDate+"&&endDate="+endDate+"&&groupName="+groupName; 
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
	function groupMainBtn(){
		location.href="${pageContext.request.contextPath}/detailGroupMain?groupName="+groupName;
	}
	function groupMemberListAll(){
		location.href="${pageContext.request.contextPath}/groupMemberList?groupName="+groupName;
	}
</script>
<style type="text/css">
.maman {
	background : linear-gradient( to bottom, lightgray, white );
	color: black;
	width: 150px;
	height: 80px;
	text-align: center;
	border-radius: 5px;
	font-size: 2.5em;
/* 	border-width: 1px 20px 1px 20px; */
}
div {
    font-size: 15px;
}
p {
    font-size: 24px;
}
.google-visualization-orgchart-lineleft {
	border-left: 1px solid #333!important;
}
.google-visualization-orgchart-linebottom {
	border-bottom: 1px solid #333!important;
}
.google-visualization-orgchart-lineright {
	border-right: 1px solid #333!important;
}
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
				<h4>그룹에 가입한 회원 리스트</h4>
				<input type="hidden" id="groupName" value="${groupName }">
				<input type="hidden" id="groupNo" value="${groupNoSend }">
				<c:choose>
					<c:when test="${memberCountResult>0 }">
						<!-- 기간 검색 -->   	
						<div class="form-inline" style="float:left;">
							<form class="form-inline" id="formSearch" name="formSearch" onsubmit="return formSearchcheck()" action="${pageContext.request.contextPath}/groupMemberListSearch" method="POST"> 
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
									<input type="button" class="btn btn-sm btn-primary" onclick="groupMemberListAll()" value="전체보기">	
								</div>				
							</form>
						</div>
						<!-- 일반 검색 -->
						<div class="form-inline pull-right">
						 	<form class="form-inline" id="formSearchKeyword" name="formSearchKeyword" onsubmit="return formSearchKeywordcheck()" action="${pageContext.request.contextPath}/groupMemberListKeywordSearch" method="post">
								<select class="form-control" name="keyOption" size="1">
								<option value="groupInviteNo" <c:if test="${'groupInviteNo'==keyOption }"> selected</c:if>>번호</option>
						            <option value="memberId" <c:if test="${'memberId'==keyOption }"> selected</c:if>>아이디</option>
						            <option value="memberName" <c:if test="${'memberName'==keyOption }"> selected</c:if>>회원명</option>
						            <option value="personalInformationApproval" <c:if test="${'personalInformationApproval'==keyOption }"> selected</c:if>>정보공개승인여부</option>
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
									<th><input type="checkbox" name="selectAll" id="selectAll" onclick="checkAll();"></th>
									<th>번호</th>
									<th>회원아이디</th>
									<th>회원명</th>
									<th>개인정보공개유무</th>
									<th>가입일</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="groupMember" items="${list }">
								<tr>
									<td><input type="checkbox" name="groupMemberCheck" value="${groupMember.memberNo}"/></td>
									<td>${groupMember.groupInviteNo }</td>
									<td>${groupMember.memberId }</td>
									<td>${groupMember.memberName }</td>
									<td>${groupMember.personalInformationApproval }</td>
									<td>${groupMember.groupInviteApprovalDate }</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						<div class="navbar-form navbar-left">
							<div  class="form-group" style="margin:0px">
								<input type="hidden" id="groupNo" value="${groupNoSend }">
								<input type="button" class="btn btn-primary" onclick="outGroupMember()" value="회원추방">
							</div>
						</div>
						<div class="navbar-form navbar-right">
							<div class="form-group" style="margin:0px">
								<input type="button" class="btn btn-primary" onclick="groupMainBtn()" value="그룹메인">
								<input type="button" class="btn btn-primary" onclick="inviteMemberBtn()" value="회원초대">
							</div>
						</div>
						<div align="center">
						<nav>
							<ul class="pagination pagination-sm">
								<c:if test="${currentPage > 10}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupMemberList?currentPage=1">&laquo;</a>
									</li>
								</c:if>
								<c:if test="${firstBlockPage > 2}">
									<li>
										<a aria-label="first" href="${pageContext.request.contextPath }/groupMemberList?currentPage=${firstBlockPage-1}">&lsaquo;</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${firstBlockPage}" end="${lastBlockPage}" step="1">
									<c:if test="${currentPage == i}">
									<li class="active">
									</c:if>
									<c:if test="${currentPage != i}">
									<li class="">
									</c:if>
										<a href="${pageContext.request.contextPath}/groupMemberList?currentPage=${i}">${i}</a>	
									</li>
								</c:forEach>	
								<c:if test="${lastBlockPage < totalBlock}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupMemberList?currentPage=${lastBlockPage+1}">&rsaquo;</a>
									</li>
								</c:if>
								<c:if test="${currentPage < lastPage}">
									<li>
										<a aria-label="last" href="${pageContext.request.contextPath}/groupMemberList?currentPage=${lastPage}">&raquo;</a>
									</li>
								</c:if>
							</ul>
						</nav>
						</div>	
					</c:when>
					<c:otherwise>
						<div align="center">
							현재 그룹에 회원이 없습니다. 회원을 초대하시겠습니까?
							<input type="button" class="btn btn-primary"  onclick="inviteMemberBtn()" value="회원초대하기">
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>