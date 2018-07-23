<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addGroup</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#inviteBtn').hide();
		$('#groupInviteMessage').hide();
	});	
	function checkmemberId(){		
		var memberId = $('#memberId').val();
		var checkCHar = /[!#$%^&*()?+=\/]/;
		if(memberId == "" || memberId == null ||checkCHar.test(memberId)){
			$('#result').html('공백 또는 특수문자가 포함된 아이디는 검색할 수 없습니다.');
			inviteForm.memberId.focus();
			return false;
		}
       var checkIdAjax = $.ajax({
            type : "GET",
            data : {memberId : memberId},
            url : "${pageContext.request.contextPath}/invitefind",
            dataType : "json",
            contentType: "application/json; charset=UTF-8"
        });    
       
       checkIdAjax.done(function(data){
    	   console.log(data);   
       	if(data.result> 0){
       		$('#result').html('한명의 회원을 찾았습니다.');
       		$('#resultInfomation').html('아이디 :'+memberId+'성함:'+data.name);
       		$('#inviteBtn').show();
       		$('#groupInviteMessage').show();
		
       	}else if(data.result == 0){
       		$('#result').html('회원을 찾지못했습니다. 다시 입력해주세요.');
			$('#inviteBtn').hide();
			$('#groupInviteMessage').hide();
       	}
       }); 
       
       checkIdAjax.fail(function(jqXHR, textStatus){
    	   alert( "Request failed: " + textStatus );
		});
	}
	function check() {
	if(confirm("회원을 초대 하시겠습니까?")){
		if(inviteForm.memberId.value == "") {
			alert("초대하실 회원아이디를 검색 해주세요.");
			inviteForm.memberId.focus();
			return false;
		  }
		if(inviteForm.groupInviteMessage.value == "") {
			alert("초대 메세지를 작성해주세요.");
			inviteForm.groupInviteMessage.focus();
			return false;
		  }else{
			alert("회원 초대 완료 되었습니다.");
			return true;
		}
	}
}

	function reset(){
		$('#groupInviteMessage').val('');
		$('#memberId').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/groupMain";
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
                         		<h4 class="title">회원초대</h4>
							</div>
							<div class="card-content">
								<form name="inviteForm" id="inviteForm" onsubmit="return check()" action="${pageContext.request.contextPath}/inviteMember" method="post">
									<input type="hidden" name="groupNo" value="${groupTable.groupNo }">
									<span>그룹명 :</span> <span id="purple">${groupTable.groupName }</span><span> 그룹 종류 : </span><span id="purple">${groupTable.groupKindName}</span>
									<div class="form-group">
										<span>초대하고자 하는  회원의 아이디를 입력해주세요.</span>
										<input class="form-control" type="text" id="memberId" name="memberId" >
										<input class="form-control" type="button" value="아이디검색하기" onclick="checkmemberId()">
										<span class="form-control" id="result"></span><br>
										<span class="form-control" id="resultInfomation"></span><br>
									</div>
									<input class="form-control" type="text" id="groupInviteMessage" name="groupInviteMessage" placeholder="초대메세지를 작성해주세요.">
									<div class="form-group">
										<input class="form-control"  class="btn btn-sm btn-primary pull-right" type="submit" id="inviteBtn" value="초대하기">
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