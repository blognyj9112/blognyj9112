<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addRefund</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript">

	function checkSum(){
		var memberPoint = ${map.memberPoint};
		console.log("memberPoint:"+memberPoint);
		var refundsum = $('#refundSum').val();
		console.log("refundSum:"+refundsum);
		if(refundsum>memberPoint){
			var result = refundsum-memberPoint;
			$('#Name').html("환불금액이 회원님의 포인트 잔액보다"+result+"많습니다. 다시입력해주세요.");
			$('#refundSum').val("");
		}else{
			alert("환불가능");
		}
	}
 	
  function check() {
		if(confirm("환불 신청하시겠습니까?")){
			if(refundForm.refundSum.value == "") {
				alert("환불금액을 입력해주세요.");
				refundForm.refundSum.focus();
				return false;
			 }
			if(refundForm.refundTitle.value == "") {
				alert("환불종류 입력해주세요.");
				refundForm.refundTitle.focus();
				return false;
			 }
			if(refundForm.refundContent.value == "") {
				alert("내용을 입력해주세요.");
				refundForm.refundContent.focus();
				return false;
			 }else{
				alert("환불 신청이 완료 되었습니다.");
				return true;
			}
		}
	}
	function reset(){
		$('#refundTitle').val('');
		$('#refundContent').val('');
		$('#refundSum').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/refundList";
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
			<div class="row">
					<div class="col-md-2">
					</div>
                     <div class="col-md-8">
					 	<div class="card">
                         	<div class="card-header" data-background-color="purple">
                         		<h4 class="title">환불 신청</h4>
							</div>
							<div class="card-content">
								<form id="refundForm" name="refundForm" onsubmit="return check()" action="${pageContext.request.contextPath}/addRefund" method="post">
									<div class="row">
										<div class="col-md-2">
										</div>
										<div class="col-md-8">	
											<span>현재 고객님이 환불할 수 있는 금액은<span id="purple"> ${map.memberPoint }</span>입니다.</span>
											<input type=hidden name="memberNo" value="${sessionScope.memberSessionNo}">
											<div class="form-group">
												<span><span id="purple">환불 금액</span>을 숫자만 입력해주세요</span>
												<input class="form-control" type="text" id="refundSum" name="refundSum" onchange="checkSum()">&nbsp;<span id="Name"></span>
											</div>
											<div class="form-group">
											<span><span id="purple">환불 제목</span>을 입력해주세요</span>
												<input class="form-control" type="text" name="refundTitle" id="refundTitle" placeholder="제목을 입력해주세요">
											</div>
											<div class="form-group">
											<span><span id="purple">환불 사유</span>를 입력해주세요</span>
												<textarea class="form-control" name="refundContent" id="refundContent" style="resize: none;" cols="40" rows="8" placeholder="내용을 입력해주세요"></textarea>
											</div>
											<div class="form-group">
												<input  type="submit" class="btn btn-sm btn-primary pull-right" value="환불신청">
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