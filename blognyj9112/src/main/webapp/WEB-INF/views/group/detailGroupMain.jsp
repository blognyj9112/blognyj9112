<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>detailGroupMain</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
	var groupName = $('#groupName').val();
	var groupNo = $('#groupNo').val();
	var memberNo = $('#memberNo').val();
	
		function groupDetail() {
			var groupNo = $('#groupNo').val();
		    location.href="${pageContext.request.contextPath}/groupDetail?groupNo="+groupNo;
		}   
		
		function groupCalendar() {
			var groupName = $('#groupName').val();
		    location.href="${pageContext.request.contextPath}/groupCalendar?groupName="+groupName;
		}   
		
		function groupRelation() {
			var groupName = $('#groupName').val();
		    location.href="${pageContext.request.contextPath}/groupMemberRelation?groupName="+groupName;
		}   
		
		function groupMemberInvite() {
			var groupNo = $('#groupNo').val();
		    location.href="${pageContext.request.contextPath}/inviteMemberForm?groupNo="+groupNo;
		}   
		
		function inviteGroupMemberList() {
			var groupNo = $('#groupNo').val();
		    location.href="${pageContext.request.contextPath}/inviteMemberList?groupNo="+groupNo;
		}   
		
		function groupMemberList() {
			var groupName = $('#groupName').val();
		    location.href="${pageContext.request.contextPath}/groupMembersList?groupName="+groupName;
		}   
		function groupMain() {
			var memberNo = $('#memberNo').val();
		    location.href="${pageContext.request.contextPath}/groupMain?memberNo="+memberNo;
		}   
		function groupMemberOut() {
			var memberNo = $('#memberNo').val();
		    location.href="${pageContext.request.contextPath}/groupOut?memberNo="+memberNo;
		}

</script>
<style type="text/css">

</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
			<input type="hidden" id="groupNo" value="${detailGroup.groupNo}">
			<input type="hidden" id="groupName" value="${groupName}">
			<input type="hidden" id="memberNo" value="${memberNo}">
				<div align="center">
					<h4>${groupName}</h4>
					<div class="btn-group" role="group">
						<input type="button" onclick="groupDetail()" class="btn  btn-sm btn-primary" value="그룹상세">
						<button onclick="groupCalendar()" class="btn btn-sm btn-primary">그룹캘린더</button>
						<button onclick="groupRelation()" class="btn btn-sm btn-primary">그룹관계도</button>
						<button onclick="groupMemberList()" class="btn btn-sm btn-primary">회원리스트</button>
						<c:if test="${result >0 }">
							<button type="button" onclick="groupMemberInvite()" class="btn btn-sm btn-primary">회원초대하기</button>
							<button type="button" onclick="inviteGroupMemberList()" class="btn btn-sm btn-primary">그룹에초대한멤버</button>
						</c:if>
						<button onclick="groupMemberOut()" class="btn btn-sm btn-primary">탈퇴하기</button>
						<button onclick="groupMain()" class="btn btn-sm btn-primary">그룹메인으로</button>
					</div>
				</div>
				<div>
					<div class="row">
						<div class="col-md-1">
						</div>
	                     <div class="col-md-10">
						 	<div class="card">
						 		<div class="card-header" data-background-color="purple">
	                         		<h4 class="title">그룹 소개</h4>
								</div>
								<div class="card-content">
									<div class="row">
										<div class="col-md-8">	
											${detailGroup.groupInfo }
										</div>
									</div>							  	
								</div>
							</div>
						</div>
					</div>
					<div class="row">
					 <div class="col-md-12">
							<div class="row">
								<div class="col-md-1">
								</div>
								<div class="col-md-5">
									<div class="card card-stats">
									 	<div class="card-header" data-background-color="orange">
									 		<i class="material-icons">content_copy</i>
										</div>
										<div class="card-content">
											<div class="row">
												<div class="col-md-5">		
													<p class="category">복약</p>
													<h4  class="title">그룹 복약 일정</h4>
												</div>
											</div>
										</div>
										<div class="card-footer">
											<div class="stats">	
												<table class="table">		
													<c:if test="${historyMedicineCount>0 }">
														<c:forEach var="addHistoryMedicine" items="${addHistoryMedicine }">
															<tr>
																<td>${addHistoryMedicine.memberName }회원님이  글을 등록하셨습니다.</td>
															</tr>
														</c:forEach>
													</c:if>
													<c:if test="${historyMedicineCount==0 }">
														<td>히스토리가 없습니다.</td>
													</c:if>
												</table>
											</div>
										</div>	
									</div>
				  				</div><!-- 복약 End --><!-- 진료 -->
								<div class="col-md-5">
									<div class="card card-stats">
									 	<div class="card-header" data-background-color="purple">
									 		<i class="material-icons">content_copy</i>
										</div>
										<div class="card-content">
											<div class="row">
												<div class="col-md-5">		
													<p class="category">진료</p>
													<h4  class="title">그룹 진료 일정</h4>
												</div>
											</div>
										</div>
										<div class="card-footer">
											<div class="stats">			
												<table class="table">
													<c:if test="${historyTreatmemtCount>0 }">
														<c:forEach var="addHistoryTreatmemt" items="${addHistoryTreatmemt }">
															<tr>
																<td>${addHistoryTreatmemt.memberName }회원님이  글을 등록하셨습니다.</td>
														</c:forEach>
													</c:if>
													<c:if test="${historyTreatmemtCount == 0 }">
														<td>히스토리가 없습니다.</td>
													</c:if>
												</table>
											</div>
						  				</div><!-- 진료 End -->
					  				</div>
				  				</div>
				  		</div>
			  			<div class="row">
				  			<div class="col-md-1">
							</div>
			  				<!-- 건강검진 -->
							<div class="col-md-5">
								<div class="card card-stats">
								 	<div class="card-header" data-background-color="purple">
								 		<i class="material-icons">content_copy</i>
									</div>
									<div class="card-content">
										<div class="row">
											<div class="col-md-5">		
												<p class="category">건강검진</p>
												<h4  class="title">그룹 건강검진 일정</h4>
											</div>
										</div>
									</div>
									<div class="card-footer">
										<div class="stats">			
												<table class="table">
													<c:if test="${historyHealthScreenCount>0 }">
														<c:forEach var="addHistoryHealthScreen" items="${addHistoryHealthScreen }">
															<tr>
																<td>${addHistoryHealthScreen.memberName }회원님이  글을 등록하셨습니다.</td>
															</tr>
														</c:forEach>
													</c:if>
													<c:if test="${historyHealthScreenCount==0 }">
														히스토리가 없습니다.
													</c:if>
												</table>
											</div>
										</div>
									</div>
								</div><!-- 건강검진 End -->
			  					<!-- 건강설문 -->
								<div class="col-md-5">
									<div class="card card-stats">
									 	<div class="card-header" data-background-color="orange">
									 		<i class="material-icons">content_copy</i>
										</div>
										<div class="card-content">
											<div class="row">
												<div class="col-md-5">	
												<p class="category">건강설문</p>
													<h4  class="title">그룹 건강설문 일정</h4>
												</div>
											</div>
										</div>
										<div class="card-footer">
											<div class="stats">
							  						<table class="table">
														<c:if test="${historyHealthSurveyCount>0 }">
															<c:forEach var="addHistoryHealthSurvey" items="${addHistoryHealthSurvey }">
																<tr>
																	<td>${addHistoryHealthSurvey.memberName }회원님이 글을 등록하셨습니다.</td>
																</tr>
															</c:forEach>
														</c:if>
														<c:if test="${historyHealthSurveyCount==0 }">
															히스토리가 없습니다.
														</c:if>
													</table>
												</div>
											</div>
									</div>
			  					</div><!-- 건강설문 End -->
				  			</div>
						</div>
					</div><!-- groupHistory End -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>