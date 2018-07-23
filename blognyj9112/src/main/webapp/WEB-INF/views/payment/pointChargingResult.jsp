<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/header.jsp"></jsp:include>
<title>pointChargingResult</title>
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
                         		<h4 class="title">결제 신청 완료</h4>
							</div>
							<div class="card-content">
									결제 신청이 완료되었습니다.
									
									현재 무통장입금만 가능합니다.
									
									00은행 
									계좌번호 : 000000-00-00000
									예금주 : (주)건강매니지먼트
									입금금액 : ${pointCharging.pointChargingSum}원 
									
									빠른 시일내에 입금 바랍니다.
								<a type="button" href="${pageContext.request.contextPath}/pointChargingList">목록으로 돌아가기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>