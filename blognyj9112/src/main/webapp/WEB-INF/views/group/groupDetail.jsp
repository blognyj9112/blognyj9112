<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupDetail</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){

	}); 
	function deleteBtn(){	
		alert('삭제가 진행됩니다.');
		var groupName= $('#groupName').val();
		var groupNo = $('#groupNo').val();
		console.log("groupNo:"+groupNo);
		console.log("groupName:"+groupName);
		var checkGroupNo = $.ajax({
			type : "GET",
	        data : {groupName : groupName},
	        url : "${pageContext.request.contextPath}/deleteGroupSearch",
	        dataType : "json",
	        contentType: "application/json; charset=UTF-8"
	    });  
		checkGroupNo.done(function(data){
		   console.log(data);   
			if(data.memberCount> 0){
		   		alert('현재 그룹에 회원이 있어 그룹 유예기간에 들어갑니다.');
		   		location.href="${pageContext.request.contextPath}/deleteGroup?groupNo="+groupNo;
			}else{
		   		alert('삭제가 완료되었습니다.');
		   		location.href="${pageContext.request.contextPath}/deleteGroup?groupNo="+groupNo;
			}
		}); 
		checkGroupNo.fail(function(jqXHR, textStatus){
			alert( "Request failed: " + textStatus );
		});
	}
	
	function modifyBtn(){	
		var groupNo = $('#groupNo').val();
		location.href="${pageContext.request.contextPath}/modifyGroup?groupNo="+groupNo;
	}
	function listBtn(){	
		history.back();
	}
	function groupMainBtn(){
		var groupName= $('#groupName').val();
		location.href="${pageContext.request.contextPath}/detailGroupMain?groupName="+groupName;
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
											<input type="hidden" id="groupNo" value="${map.groupDetail.groupNo}">
											<input type="hidden" id="groupName" value="${map.groupDetail.groupName}">
												<table class="table table-hober" id="tableCss">
													<tr>
														<td>그룹명</td>
														<td>${map.groupDetail.groupName }</td>
													<tr>
													<tr>
														<td>그룹종류</td>
														<td>${map.groupDetail.groupKindName }</td>
													<tr>
													<tr>
														<td>그룹장</td>
														<td>${map.groupDetail.memberName }</td>
													<tr>
													<tr>
														<td>그룹설명</td>
														<td>${map.groupDetail.groupInfo }</td>
													<tr>
													<tr>
														<td>생성일</td>
														<td>${map.groupDetail.groupCreateDate }</td>
													</tr>
												</table>
												<form class="form-inline">
													<c:if test="${map.countNext > 0 }">
														<div class="form-group">
															<p><span id="purple">다음글</span> : <a type="button" href="${pageContext.request.contextPath}/groupDetail?groupNo=${map.nextGroup.groupNo}">${map.nextGroup.groupName }</a></p>
														</div>
													</c:if>
													<c:if test="${map.countNext eq 0 || map.countNext == null}">
														<div class="form-group">
															<p><span id="purple">다음글</span>이 없습니다.</p>
														</div>
													</c:if>
													<c:if test="${map.countPrev > 0 }">
														<div class="form-group pull-right">
															<p><span id="purple">이전글</span> : <a type="button" href="${pageContext.request.contextPath}/groupDetail?groupNo=${map.prevGroup.groupNo}">${map.prevGroup.groupName }</a></p>
														</div>
													</c:if>
													<c:if test="${map.countPrev eq 0 || map.countPrev == null}">
														<div class="form-group pull-right">
															<p><span id="purple">이전글</span>이 없습니다.</p>
														</div>
													</c:if>
												</form>
												<div class="navbar-form navbar-left">
													<div class="form-group" style="margin:0px">
														<button class="btn btn-primary" onclick="listBtn()">목록</button>
													</div>
												</div>
												<div class="navbar-form navbar-right">
													<div class="form-group" style="margin:0px">
														<c:if test="${sessionScope.memberSessionNo == map.groupDetail.memberNo}">
														<button class="btn btn-primary" onclick="deleteBtn()">삭제</button>
														<button class="btn btn-primary" onclick="modifyBtn()">수정</button>
														</c:if>
														<button class="btn btn-primary" onclick="groupMainBtn()">그룹메인</button>
													</div>
												</div>
											<div>
										</div>
									</div>
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