<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupMain</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
	function groupListBtn(){
		location.href="${pageContext.request.contextPath}/groupList";
	}
	function addGroupBtn(){
		location.href="${pageContext.request.contextPath}/addGroup";
	}
	function inviteGroupBtn(){
		location.href="${pageContext.request.contextPath}/inviteGroupList";
	}
</script>
<style>
#center{
width: 600;
margin: 0 auto;
}
#purple{
color: #9c27b0;
 font-weight: bold;
}
h4{
font-weight: bold;
}
</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
		            <h4>그룹메인</h4>
		            <div class="container-fluid">
					<div class="row">
	                   <div class="col-lg-6 col-md-12">
						 	<div class="card">
								<div class="card-header" data-background-color="purple">
									<h4 class="title">생성한 그룹</h4>
									<p class="category">생성한 그룹을 확인할 수 있습니다.</p>
	                            </div>
								<div class="card-content">
									<p class="category">회원님의 그룹 생성 상태</p>
									<c:if test="${map.creationResult >0 }">
									<h4 class="title">현재 <span id="purple">${map.creationResult }</span>개의 그룹을 생성하셨습니다.</h4>
									</c:if>
									<c:if test="${map.creationResult eq 0 ||map.creationResult eq null}">
										<h4 class="title">현재 생성하신 그룹이 업습니다.</h4>
									</c:if>
								</div>
								<div class="card-footer">
									<div class="stats">
										<p>생성한 그룹 리스트</p>
										<c:if test="${map.creationResult >0 }">
											<c:forEach var="groupCreation" items="${map.groupCreateList}">
												<a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/detailGroupMain?groupName=${ groupCreation.groupName}">${ groupCreation.groupName}</a>
											</c:forEach>	
										</c:if>
										<p>삭제 진행중인 그룹 리스트</p>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-md-12">
							<div class="card">
								<div class="card-header" data-background-color="orange">
									<h4 class="title">가입한 그룹</h4>
									<p class="category">가입한 그룹을 확인할 수 있습니다.</p>
			                    </div>
								<div class="card-content">
									<p class="category">회원님의 그룹 가입 상태</p>
									<c:if test="${map.result > 0 }">
										<h4 class="title">회원님은 현재 <span id="purple">${map.result }</span>개의 그룹에 가입되어 있습니다.</h4>
									</c:if>
									<c:if test="${map.result == 0 }">
										<h4 class="title">현재 가입한 그룹이 없습니다.</h4>
									</c:if>
								</div>
								<div class="card-footer">
									<div class="stats">
										<p>가입한 그룹 리스트</p>
										<c:if test="${map.result > 0 }">
											<c:forEach var="groupTable" items="${map.groupJoinList }">
												<a class="btn btn-sm btn-primary" type="button" href="${pageContext.request.contextPath}/detailGroupMain?groupName=${ groupTable.groupName}">${ groupTable.groupName}</a>
											</c:forEach>
										</c:if>
										<p>삭제 진행중인 그룹 리스트</p>
									</div>	
								</div>
							</div>
						</div>	
					</div>
					<div class="navbar-form navbar-left">
						<div class="form-group" style="margin:0px">
							<c:if test="${sessionScope.memberSessionLevel == 1 }">
								<button class="btn btn-primary" onclick="groupListBtn()">그룹리스트</button>
							</c:if>
							<button class="btn btn-primary" onclick="addGroupBtn()">그룹생성</button>
						</div>
					</div>
					<div class="navbar-form navbar-right">
						<div class="form-group" style="margin:0px">
							<button class="btn btn-primary" onclick="inviteGroupBtn()">초대리스트</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>