<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addPointToMember</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
	function check() {
	if(confirm("포인트를 지급하시겠습니까?")){
		if(addPointForm.memberNo.value == "") {
			alert("회원 번호를 입력해주세요.");
			addPointForm.memberNo.focus();
			return false;
		 }
		if(addPointForm.pointChargingSum.value == "") {
			alert("포인트를 입력해주세요.");
			addPointForm.pointChargingSum.focus();
			return false;
		 }else{
			alert("완료 되었습니다.");
			return true;
		}
	}
	function reset(){
		$('#pointChargingSum').val('');
		$('#memberNo').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/pointChargingList";
	}  
</script>
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
                         		<h4 class="title">회원에게 포인트 지급</h4>
							</div>
							<div class="card-content">
								<form id="addPointForm" onsubmit="return check()" action="${pageContext.request.contextPath}/addPoint" method="POST">
									<div class="row">
										<div class="col-md-2">
										</div>
										<div class="col-md-8">	
											<div class="form-group">
											<span><span id="purple">회원번호</span>를 입력해주세요</span> 
											<input class="form-control" type="text" id="memberNo" name="memberNo" placeholder="회원번호를 입력해주세요">
											</div>
											<div class="form-group">
											<span>지급할<span id="purple"> 포인트 금액</span>를 입력해주세요</span>
											<input class="form-control" type="text" id="pointChargingSum" name="pointChargingSum" placeholder="숫자만 입력해주세요">
											</div>
											<div class="form-group">
												<input type="submit" class="btn btn-sm btn-primary pull-right" value="포인트지급">
											</div>
										</div>
									</div>
								</form>
								<div class="navbar-form navbar-left">
									<div class="form-group" style="margin:0px">
										<input type="button" class="btn btn-primary" onclick="returnListBtn()" value="목록으로">
									</div>
								</div>
								<div class="navbar-form navbar-right">
									<div class="form-group" style="margin:0px">
										<input type="button" class="btn btn-primary" onclick="reset()" value="다시입력">
										<input type="button" class="btn btn-primary" onclick="returnBtn()" value="등록취소">
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