<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addGroup</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
//그룹종류 리스트
	$(document).ready(function(){
		groupKindListajax();
	});
	function groupKindListajax(){		
		var groupKindListAjax = $.ajax({
		    type : "POST",
		    url : "${pageContext.request.contextPath}/groupKindListSelect",
		    dataType : "json",
		    contentType: "application/json; charset=UTF-8"
		}); 
		groupKindListAjax.done(function(data){
			console.log(data.list.length); 
			console.log(data.list[1]);
			var list = data.list;
			if(list.length > 0){
		       	for (var i=0; i<list.length; i++){
		       		var groupKindNo = list[i].groupKindNo;
		       		var groupKindName = list[i].groupKindName;
		       		$('#groupKindNo').append('<option value="'+groupKindNo+'">'+groupKindName+'</option>');
		       	}
		     }
		}); 
		groupKindListAjax.fail(function(jqXHR, textStatus){
			alert( "Request failed: " + textStatus );
		});
	}
var count = 0;
	function checkName(){		
		var groupName = $('#groupName').val();
		var checkCHar = /[!#$%^&*()?+=\/]/;
		if(groupName == "" || groupName == null ||checkCHar.test(groupName)){
			$('#Name').html('공백 또는 특수문자가 포함된 그룹명은 사용할 수 없습니다.');
			groupForm.groupName.focus();
			return false;
		}
       var checkName = $.ajax({
            type : "GET",
            data : {groupName : groupName},
            url : "${pageContext.request.contextPath}/checkGroupName",
            dataType : "json",
            contentType: "application/json; charset=UTF-8",
            success : function(data){
            	console.log(data);   
            	if(data.result> 0){
            		$('#Name').html('입력하신 그룹명은 현재 사용중이므로 사용할 수 없습니다. 다시 입력해주세요.');
            		return count = 0;
            	}else if(data.result == 0){
            		$('#Name').html('사용가능한 그룹명입니다.');
            		return count = 1;
            	}
            }
        });       
       checkName.fail(function(jqXHR, textStatus){
    	   alert( "Request failed: " + textStatus );
		});
	}

	function check() {
	if(confirm("그룹을 생성하시겠습니까?")){
		if(groupForm.groupName.value == "") {
			alert("그룹명을 입력해주세요.");
			groupForm.groupName.focus();
			return false;
		  }
		if(groupForm.groupInfo.value == ""){
			alert("그룹 소개글을 해주세요.");
			groupForm.groupInfo.focus();
			return false;
		}if(groupForm.groupKindNo.value == "선택해주세요"){
			alert("그룹 종류를 선택해주세요.");
			groupForm.groupKindNo.focus();
			return false;
		}else{
			alert("그룹 생성이 완료 되었습니다.");
			return true;
		}
	}
}

	function reset(){
		$('#groupName').val('');
		$('#groupInfo').val('');
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
                         		<h4 class="title">그룹 등록</h4>
							</div>
							<div class="card-content">
									<form name="groupForm" id="groupForm" onsubmit="return check()" action="${pageContext.request.contextPath}/addGroup" method="post">
										<div class="row">
											<div class="col-md-2">
											</div>
											<div class="col-md-8">	
												<input type="hidden" name="memberNo" value="${sessionScope.memberSessionNo}">
												<div class="form-group">
													<span><span id="purple">그룹 종류</span>를 선택해주세요</span> 
													<select class="form-control" id="groupKindNo" name="groupKindNo">
													  <option value="선택해주세요" selected="selected">선택해주세요</option>
													</select>
												</div>
												<div class="form-group">
													<span><span id="purple">그룹명</span>을 입력해주세요</span> 
													<input type="text" class="form-control"  id="groupName" name="groupName" maxlength="10" onchange="checkName()" placeholder="그룹명을 입력해주세요">&nbsp;<span id="Name"></span>
												</div>
												<div class="form-group">
													<span><span id="purple">그룹소개글</span>을 입력해주세요</span> 
													<textarea class="form-control" id="groupInfo" name="groupInfo" style="resize: none;" cols="40" rows="8" placeholder="그룹 소개글을 입력해주세요"></textarea>
												</div>
												<div class="form-group">
													<input class="btn btn-sm btn-primary pull-right" type="submit" value="등록">
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
											<input type="button" class="btn btn-primary" onclick="returnBtn()" value="취소">
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