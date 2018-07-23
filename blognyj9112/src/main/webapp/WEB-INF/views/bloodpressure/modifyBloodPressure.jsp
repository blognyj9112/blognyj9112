<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modifyBloodPressure</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	
});
function check() {
	var checkCHar = /^[0-9]*$/;
	var systolicPressure = $('#systolicPressure').val();
	var diastolicPressure = $('#diastolicPressure').val();
	if(confirm("혈압정보를 수정하시겠습니까?")){
		if(!checkCHar.test(systolicPressure)){
			alert("숫자만 입력할 수 있습니다.");
			bloodPressureForm.systolicPressure.focus();
			return false;
		}
		if(!checkCHar.test(diastolicPressure)){
			alert("숫자만 입력할 수 있습니다.");
			bloodPressureForm.diastolicPressure.focus();
			return false;
		}
		if(bloodPressureForm.systolicPressure.value == "") {
			alert("이완기 혈압을 입력해주세요.");
			bloodPressureForm.systolicPressure.focus();
			return false;
		  }
		if(bloodPressureForm.diastolicPressure.value == ""){
			alert("수축기 혈압을 해주세요.");
			bloodPressureForm.diastolicPressure.focus();
			return false;
		}else{
			alert("혈압 정보가 수정 완료 되었습니다.");
			return true;
		}
	}
}
	function reset(){
		$('#systolicPressure').val('');
		$('#diastolicPressure').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/bloodPressure";
	}
</script>
<style>
#purple{
color: #9c27b0;
font-weight: bold;
}
h4{
font-weight: bold;
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
                         		<h4 class="title">수정할 내용을 적어주세요.</h4>
							</div>
							<div class="card-content">
								<form id="bloodPressureForm" onsubmit="return check()" action="${pageContext.request.contextPath}/modifyBloodPressure" method="post">
									<div class="row">
										<div class="col-md-2">
										</div>
										<div class="col-md-8">	
											<input type="hidden" name="bloodPressureNo" value="${bloodPressure.bloodPressureNo }">
											<div class="form-group">
												<span><span id="purple">등록번호 :</span> ${bloodPressure.bloodPressureNo }</span><br>
												<span><span id="purple">등록일 :</span> ${bloodPressure.bloodPressureDate }</span>
											</div>
											<div class="form-group">
												<span><span id="purple">수축기혈압</span>을 입력해주세요</span> 
												<input class="form-control" type="text" id="systolicPressure" name="systolicPressure" placeholder="${bloodPressure.systolicPressure}">
											</div>
											<div class="form-group">
												<span><span id="purple">이완기혈압</span>을 입력해주세요</span> 
												<input class="form-control" type="text" id="diastolicPressure" name="diastolicPressure" placeholder="${bloodPressure.diastolicPressure}">
											</div>
											<div class="form-group">
												<input type="submit" class="btn btn-sm btn-primary pull-right" value="수정">
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
										<input type="button" class="btn btn-primary" onclick="returnBtn()" value="수정취소">
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