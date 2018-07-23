<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modifyGroup</title>
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
	function check() {
	if(confirm("그룹정보를 수정하시겠습니까?")){
		if(groupForm.groupInfo.value == ""){
			alert("그룹 소개글을 해주세요.");
			groupForm.groupInfo.focus();
			return false;
		}else{
			alert("그룹 정보가 수정 완료 되었습니다.");
			return true;
		}
	}
}
	function reset(){
		$('#memberId').val('');
		$('#groupInfo').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		var groupNo = $('#groupNo').val();
		location.href="${pageContext.request.contextPath}/groupMainDetail?groupNo="+groupNo;
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
                         		<h4 class="title">그룹 수정</h4>
							</div>
							<div class="card-content">
								<form name="groupForm" id="groupForm" onsubmit="return check()" action="${pageContext.request.contextPath}/modifyGroup" method="post">
									<div class="row">
											<div class="col-md-2">
											</div>
											<div class="col-md-8">	
												<input class="form-control" type="hidden" name="groupNo" value="${groupTable.groupNo }">
												<div class="form-group">
													<span>현재 그룹 종류는<span id="purple"> ${groupTable.groupKindName}</span>입니다. 변경하실 그룹 종류를 선택해주세요.</span>
													<select class="form-control" id="groupKindNo" name="groupKindNo">
													  <option value="선택해주세요" selected="selected">선택해주세요</option>
													</select>
												</div>
												<div class="form-group">
													<span>그룹명 : <span id="purple">${groupTable.groupName }</span> <br>※그룹명은 변경이 불가능합니다.※</span>
												</div>
												<div class="form-group">
													<span>그룹 소개:</span>
													<textarea class="form-control" id="groupInfo" style="resize: none;" cols="40" rows="8" name="groupInfo" placeholder="현재 소개글 내용은 ${groupTable.groupInfo }입니다."></textarea>
												</div>
												<div class="form-group">
												<input class="btn btn-sm btn-primary pull-right" type="submit" value="수정">
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