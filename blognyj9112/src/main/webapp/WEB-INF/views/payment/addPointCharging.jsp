<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/header.jsp"></jsp:include>
<title>addPointCharging</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
	function check() {
		
		if(confirm("포인트 결제를 신청하시겠습니까?")){
			
			var pointChargingRoot = $("input[type=radio]:checked").val();
			if(pointChargingForm.pointChargingNumber.value == "") {
				alert("전화번호를 입력해주세요.");
				pointChargingForm.pointChargingNumber.focus();
				return false;
			}if(pointChargingRoot == "" || pointChargingRoot == null|| pointChargingRoot!= 3) {
				alert("결제방식을 선택해주세요.");
				pointChargingForm.selectedPointChargRoot.focus();
				return false;
			}else{
				alert("결제 신청이 완료 되었습니다.");
				return true;
			}
		}
	}
	function checkpointChargingNumber(){
		var selectedPointChargingNumber = $("#pointChargingNumber").val();
		 var number = /^[0-9]*$/;
		if(!number.test(selectedPointChargingNumber)){
			alert('숫자만 입력해주세요.');
			pointChargingForm.pointChargingNumber.focus();
			return false;
		}else{
			$('#selectedPointChargingNumber').html(selectedPointChargingNumber);
		}
	}
	function checkpointChargingEmail(){
		var selectedPointChargingEmail = $("#pointChargingEmail").val();
		$('#selectedPointChargingEmail').html(selectedPointChargingEmail);
	}
	function checkpointChargingSum(){
		var pointChargingSum = $("#pointChargingSum option:selected").val();
		$('#selectedPointChargingSum').html(pointChargingSum);
	}
	function checkpointChargingRoot(){
		var pointChargingRoot = $("input[type=radio]:checked").val();
		if(pointChargingRoot != 3){
			alert('현재 무통장 입금만 가능합니다.');
			return false;
		}else{
			$('#pointChargingRoot').html('결제신청 버튼을 누르면 선택하신 결제방식으로 결제가 진행됩니다.');
		}
	}
	function reset(){
		$('#pointChargingSum').val('');
		$('#pointChargingNumber').val('');
		$('#pointChargingEmail').val('');
		$('#selectedPointChargingNumber').html('');
		$('#selectedPointChargingEmail').html('');
		$('#selectedPointChargingSum').html('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/pointChargingList";
	} 

</script>
<style>
#center{
width: 1300;
margin: 0 auto;
}
#incenter{
width: 1000;
margin: 0 auto;
}
#purple{
color: #9c27b0;
 font-weight: bold;
}
h4{
font-weight: bold;
}
#chargingBorder {
    border: 1px solid black;
    padding: 20px;
    border-radius: 5px;
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
				<div id="center">
					<div class="col-md-1">
					</div>
                     <div class="col-md-10">
					 	<div class="card">
                         	<div class="card-header" data-background-color="purple">
                         		<h4 class="title">포인트 결제 신청</h4>
							</div>
							<div class="card-content">
								<form id="pointChargingForm" onsubmit="return check()" action="${pageContext.request.contextPath}/pointCharging" method="post">
									<div class="row">
										<div id="incenter">
										<div class="col-md-1">
											</div>
											<div class="col-md-5">	
											<p></p>
												<input type="hidden" name="memberNo" value="${sessionScope.memberSessionNo}">
												<input type="hidden" id="memberPoint" value="${pointCharging.memberPoint}">
												<div class="form-group">
													<span id="purple">${sessionScope.memberSessionId }</span><span>님! 안녕하세요.</span>
												</div>
												<div class="form-group">
													<span>현재 잔액 : <span id="purple">${pointCharging.memberPoint }</span></span>
												</div>
												<div class="form-group">
													<span><span id="purple">전화번호</span>를 입력해주세요</span>
													<input type="text" class="form-control" id="pointChargingNumber" name="pointChargingNumber" onchange="checkpointChargingNumber()" placeholder="전화번호를 입력해주세요">
												</div>
												<div class="form-group">
													<span>결제한 내역을 받고 싶으시면 <span id="purple">이메일</span>을 입력해주세요.</span><br>
													<input type="text" class="form-control" id="pointChargingEmail" name="pointChargingEmail" onchange="checkpointChargingEmail()" placeholder="이메일를 입력해주세요">
													<span id="purple">올바른 형식으로 입력해주셔야 발송이 됩니다.</span>
												</div>
												<div class="form-group">
													<span><span id="purple">결제할 금액</span>을 선택해주세요</span>
													<select class="form-control" id="pointChargingSum" onchange="checkpointChargingSum()" name="pointChargingSum">
														<option value="3000" selected="selected">3000</option>
														<option value="5000">5000</option>
														<option value="10000">10000</option>
														<option value="15000">15000</option>
														<option value="20000">20000</option>
														<option value="25000">25000</option>
													</select>
												</div>
											</div>
											<div class="col-md-1">
											</div>	
											<div class="col-md-5">	
												<div id="chargingBorder">
													<p>포인트 결제 신청서</p>
													<div class="form-group">
														<span>아이디</span> <span id="purple"><span class="pull-right">${sessionScope.memberSessionId }</span></span>
													</div>
													<div class="form-group">	
														<span>전화번호</span> <span id="purple"><span class="pull-right" id="selectedPointChargingNumber"></span></span>
													</div>
													<div class="form-group">	
														<span>이메일</span> <span id="purple"><span class="pull-right" id="selectedPointChargingEmail"></span></span>
													</div>
													<div class="form-group">	
														<span>충전금액</span> <span id="purple"><span class="pull-right" id="selectedPointChargingSum"></span></span>
													</div>
													<div class="form-group">
														<span><span id="purple">결제 방식</span>을 선택해주세요</span>
														<div class="form-group" id="selectedPointChargRoot" onchange="checkpointChargingRoot()">
															<input class="form-group" type="radio" name="gener" value="1">실시간 계좌 이제<br>
															<input class="form-group" type="radio" name="gener" value="2">가상계좌 결제<br>
															<input class="form-group" type="radio" name="gener" value="3">무통장 입금<br>
														</div>
													</div>
													<span id="pointChargingRoot"></span>
													<div class="form-group">
														<input type="submit" class="btn btn-primary pull-right" value="결제신청">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-1">
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
	</div>
</body>
</html>