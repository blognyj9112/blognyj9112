<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modifyGroupKind</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
function check() {
	if(confirm("그룹 종류명를 수정하시겠습니까?")){
		if(groupForm.groupKindName.value == "") {
			alert("그룹 종류명를 입력해주세요.");
			groupForm.groupKindName.focus();
			return false;
		  }else{
			alert("수정이 완료 되었습니다.");
			return true;
		}
	}
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
                         		<h4 class="title">그룹 종류 수정</h4>
							</div>
							<div class="card-content">
									<form name="groupForm" id="groupForm" onsubmit="return check()" action="${pageContext.request.contextPath}/modifyGroupKind" method="post">
										<div class="row">
											<div class="col-md-2">
											</div>
											<div class="col-md-8">	
												<div class="form-group">
												<input class="form-control" type="hidden" name="groupKindNo" value="groupKindNo">
													<span>현재 그룹종류명은 <span>${groupKindName }</span>입니다. 수정하실 그룹명을 입력해주세요.</span>
													<input class="form-control" type="text" name="groupKindName" placeholder="${groupKindName }">
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