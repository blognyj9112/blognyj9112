<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exerciseFeedResponse</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<script type="text/javascript">
	var exerciseId = 1;
	var foodId = 1;
	
	$(document).ready(function(){
		var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
		$('#foodFeedback').hide();
		$('#exerciseFeedback').hide();
		$('#searchResult').hide();
	});
		
	//음식 피드백 검색
	function foodFeedbackBtn(){
		$('#foodFeedback').show();
	}
	function checkFoodInfo(){		
		var foodName = $('#foodName').val();
		var foodGroup = $('#foodGroup option:selected').val();
		console.log("foodGroup:"+foodGroup);
		console.log("foodName:"+foodName);
       var checkFoodAjax = $.ajax({
            type : "GET",
            data : {foodGroup : foodGroup, foodName : foodName},
            url : "${pageContext.request.contextPath}/feedbackFoodSearch",
            dataType : "json",
            contentType: "application/json; charset=UTF-8"
        });    
       
		checkFoodAjax.done(function(data){
			console.log(data);   
			if(data.result > 0){
				$('#searchResult').show();
	       		$('#foodSearchResult').html(data.result+'개의 음식을 찾았습니다.');
	       	 	$("#food option").remove();
	   	    	 var option = $("#food option").size();
	   	    	console.log("option length :"+option);
	       	 	if(option==0){
		       		for (var i=0; i<data.foodInfo.length; i++){
		       			var foodName = data.foodInfo[i].foodName;
		       			var Kcal = data.foodInfo[i].foodKcal;
		       			var foodNo = data.foodInfo[i].foodNo;
		       			$('#food').append('<option value="'+foodNo+'">'+foodName +'('+ Kcal +'kcal/100g)</option>');
		       		}
	       	 	}
		     }else if(data.result == 0){
		       		$('#foodSearchResult').html('음식을 찾지못했습니다. 다시 입력해주세요.');
		     }
		}); 
		checkFoodAjax.fail(function(jqXHR, textStatus){
			alert( "Request failed: " + textStatus );
		});
   	}
	
	//음식 등록
	function foodAddBtn() {
		var foodInfoNo = $("#food option:selected").val();
		var foodNameSelect = $("#food option:selected").text();
		var ingestionAmount = $('#ingestionAmount').val();
		var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
		var checkCHar = /^[0-9]*$/;
		if(!checkCHar.test(ingestionAmount)){
			alert('숫자만 입력할 수 있습니다.');
			exercisefeedbackForm.ingestionAmount.focus();
			return false;
		}
		if(ingestionAmount == ''){
			alert('음식양을 적어주세요.');
			exercisefeedbackForm.ingestionAmount.focus();
			return false;
		}
		if(foodInfoNo == '1'){
			alert('하단의 음식을 선택해주세요');
			return false;
		}else{		
	       var addFoodAjax = $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/addFoodFeedback?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo+"&&foodInfoNo="+foodInfoNo+"&&ingestionAmount="+ingestionAmount
	            ,dataType : "json",
	            contentType: "application/json; charset=UTF-8"
	       });   
	       
	       addFoodAjax.done(function(msg){
			if(msg>0){
				alert('음식이 데이터에 저장되었습니다.');
				
				var input0 = document.createElement("p");
				input0.setAttribute("id","food"+foodId);
				input0.setAttribute("class","form-control");
				input0.append(foodNameSelect+'가 선택되었습니다.');
				document.getElementById('foodSelect').appendChild(input0);
				
				var input1 = document.createElement("input");
	    		input1.setAttribute("type","hidden");
	    		input1.setAttribute("id","food"+foodId);
	    		input1.setAttribute("name","foodInfoNo");
	    		input1.setAttribute("value",foodInfoNo);
	    		document.getElementById('foodSelect').appendChild(input1);
	    		
				var input3 = document.createElement("input");
				input3.setAttribute("type","button");
				input3.setAttribute("onclick","deleteFBtn(this)");
				input3.setAttribute("id","food"+foodId);
				input3.setAttribute("class","btn btn-xs btn-default");
				input3.setAttribute("value","삭제");
				document.getElementById('foodSelect').appendChild(input3);
				
				document.getElementById('exerciseFeedbackContent').append('음식명:'+foodNameSelect+'양:'+ingestionAmount+'/100g');	
				$('#foodFeedback').hide();
			}
	   		
	       });
	       addFoodAjax.fail(function(jqXHR, textStatus){
				alert( "Request failed: " + textStatus );
			});
		}
	}
		
 	//운동피드백 
	function exerciseFeedbackBtn(){
		$('#exerciseFeedback').show();
		
       var checkAjax = $.ajax({
            type : "GET",
            url : "${pageContext.request.contextPath}/exerciseSearch",
            dataType : "json",
            contentType: "application/json; charset=UTF-8"
        });    
       
       checkAjax.done(function(msg){
			console.log(msg);   
 	       	for (var i=0; i<msg.exerciseSearch.length; i++){
	       		var exerciseName = msg.exerciseSearch[i].exerciseName;
	       		var Kcal = msg.exerciseSearch[i].exerciseCalorie;
	       		var exerciseNo = msg.exerciseSearch[i].exerciseNo;
	       		$('#exercise').append('<option value="'+exerciseNo+'">'+ exerciseName +'('+ Kcal +'kcal/min)</option>');
      	 	}
		}); 
       checkAjax.fail(function(jqXHR, textStatus){
			alert( "Request failed: " + textStatus );
		});
   	}
	//운동피드백 등록
	function exerciseAddBtn() {
		var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
		var exerciseNo = $("#exercise option:selected").val();
		var exerciseNameSelect = $("#exercise option:selected").text();
		var exerciseTime = $('#exerciseTime').val();
		var checkCHar = /^[0-9]*$/;
		if(!checkCHar.test(exerciseTime)){
			alert('숫자만 입력할 수 있습니다.');
			exercisefeedbackForm.exerciseTime.focus();
			return false;
		}
		if(exerciseTime == ''){
			alert('운동 시간을 적어주세요.');
			exercisefeedbackForm.exerciseTime.focus();
			return false;
		}
		if(exerciseNo == '1'){
			alert('하단의 운동을 선택해주세요');
			return false;
		}else{
	       var addexerciseAjax = $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/addExerciseFeedback?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo+"&&exerciseNo="+exerciseNo+"&&exerciseTime="+exerciseTime
	            ,dataType : "json",
	            contentType: "application/json; charset=UTF-8"
	       });  
	       addexerciseAjax.done(function(msg){
				if(msg>0){
					alert('운동이 데이터에 저장되었습니다.');
		    		console.log("exerciseId현재개수:"+exerciseId);

		    		var input0 = document.createElement("p");
		    		input0.setAttribute("id","exercise"+exerciseId);
		    		input0.setAttribute("class","form-control");
		    		input0.append('운동:'+exerciseNameSelect+'시간:'+exerciseTime+'/min');
		    		document.getElementById('exerciseSelect').appendChild(input0);	
		    		
		    		var input1 = document.createElement("input");
		    		input1.setAttribute("type","hidden");
		    		input1.setAttribute("id","exercise"+exerciseId);
		    		input1.setAttribute("name","exerciseNo");
		    		input1.setAttribute("value",exerciseNo);
		    		document.getElementById('exerciseSelect').appendChild(input1);
		    		
		    		var input3 = document.createElement("input");
		    		input3.setAttribute("type","button");
		    		input3.setAttribute("onclick","deleteBtn(this)");
		    		input3.setAttribute("id","exercise"+exerciseId);
		    		input3.setAttribute("value","삭제");
		    		input3.setAttribute("class","btn btn-xs btn-default");
		    		document.getElementById('exerciseSelect').appendChild(input3);
		    		
		    		document.getElementById('exerciseFeedbackContent').append('운동:'+exerciseNameSelect+'시간:'+exerciseTime+'/min');	
		    		exerciseId++;
		    		$('#exerciseFeedback').hide();
	    	   }
	       });  
	       addexerciseAjax.fail(function(jqXHR, textStatus){
				alert( "Request failed: " + textStatus );
			});
	       }
		}

    //운동 삭제
    function deleteBtn(thisid) {
    	var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
    	var parentNode = document.getElementById('exerciseSelect');
    	var idSelect = document.getElementById(thisid.getAttribute('id')).getAttribute('id');
    	var exerciseNo = $("input[id="+idSelect+"]").val();
    	var counted = $('input[id='+idSelect+']').length;
    	 var deleteExerciseAjax = $.ajax({
	            type : "GET",
	            url : "${pageContext.request.contextPath}/deleteExerciseFeedback?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo+"&&exerciseNo="+exerciseNo
	            ,dataType : "json",
	            contentType: "application/json; charset=UTF-8"
	       });  
			deleteExerciseAjax.done(function(msg){
				if(msg>0){
					alert('데이터에서 삭제가 완료되었습니다.');
					parentNode.removeChild(document.getElementById(idSelect)); 
			     	for(var i =0; i<counted; i++){
			    		parentNode.removeChild(document.getElementById(idSelect)); 
			    		console.log('삭제완료');
			    	}  
				}
	       });  
			deleteExerciseAjax.fail(function(jqXHR, textStatus){
				alert( "Request failed: " + textStatus );
			});
	} 
    //음식 삭제
    function deleteFBtn(thisid) {
    	var exerciseFeedbackRequestNo = $('#exerciseFeedbackRequestNo').val();
    	var parentNode = document.getElementById('foodSelect');
    	var idSelect = document.getElementById(thisid.getAttribute('id')).getAttribute('id');
    	console.log("food idSelect:"+idSelect);
    	var foodInfoNo = $("input[id="+idSelect+"]").val();
    	var count = $('input[id='+idSelect+']').length;
   	 var deleteFoodAjax = $.ajax({
         type : "GET",
         url : "${pageContext.request.contextPath}/deleteFoodFeedback?exerciseFeedbackRequestNo="+exerciseFeedbackRequestNo+"&&foodInfoNo="+foodInfoNo
         ,dataType : "json",
         contentType: "application/json; charset=UTF-8"
    });  
   	deleteFoodAjax.done(function(msg){
			if(msg>0){
				alert('데이터에서 삭제가 완료되었습니다.');
		     	parentNode.removeChild(document.getElementById(idSelect)); 
		    	for(var i =0; i<count; i++){
		    		parentNode.removeChild(document.getElementById(idSelect)); 
		    		console.log('삭제완료');
		    	} 
			}
    });  
   	deleteFoodAjax.fail(function(jqXHR, textStatus){
			alert( "Request failed: " + textStatus );
		});
    }
    //이전
	function cancleBtn() {
		history.back();
	} 	
    //리스트 돌아가기
	function exerciseFeedbackListBtn() {
		location.href="${pageContext.request.contextPath}/exerciseFeedbackRequestList";
	} 	
    
    //등록전 체크
    function check(){
    	if(exercisefeedbackForm.exerciseFeedbackTitle.value == "") {
    		alert('제목을 입력해주세요.');
    		exercisefeedbackForm.exerciseFeedbackTitle.focus();
    		return false();
    	}
    	if(exercisefeedbackForm.exerciseFeedbackContent.value == "") {
    		alert('내용을 입력해주세요.');
    		exercisefeedbackForm.exerciseFeedbackContent.focus();
    		return false();
    	}else{
    		alert('피드백 등록 완료되었습니다.');
    		return true();
    	}
	}
	function reset(){
		$('#foodSelect').val('');
		$('#exerciseSelect').val('');
		$('#exerciseFeedbackTitle').val('');
		$('#exerciseFeedbackContent').val('');
	}
	function returnBtn(){
		history.back();
	}
	function returnListBtn(){
		location.href="${pageContext.request.contextPath}/exerciseFeedbackList";
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
                         		<h4 class="title">운동피드백 답변 작성</h4>
							</div>
							<div class="card-content">
								<div class="row">
									<div class="col-md-2">
									</div>
									<div class="col-md-8">	
								<form class="form-horizontal" id="exercisefeedbackForm" onsubmit="return check()" action="${pageContext.request.contextPath}/addExerciseFeedbackRequestResult" method="post">
									<div>
										<input type="text" class="form-control" id="exerciseFeedbackTitle" name="exerciseFeedbackTitle" placeholder="제목을 입력해주세요">
									</div>
									<div class="form-group">
										<input type="hidden" id="memberNo" name="memberNo" value="${exerciseFeedbackset.memberNo }">
										<input type="hidden" id="exerciseFeedbackRequestNo" name="exerciseFeedbackRequestNo" value="${exerciseFeedbackset.exerciseFeedbackRequestNo}">
									</div>
									<textarea class="form-control" id="exerciseFeedbackContent" name="exerciseFeedbackContent" style="resize: none;" cols="40" rows="8" placeholder="내용을 입력해주세요"></textarea>
									<div class="form-group">
									<input id="submitBtn" class="btn btn-sm btn-primary pull-right" type="submit" value="등록하기">
									</div>
								</form>
								<div class="form-group">
									<input type="button" class="btn btn-sm btn-primary pull-left" onclick="foodFeedbackBtn()" value="음식피드백 등록하기">
								</div>
									<div id="foodFeedback">
										<div>
											음식을 검색해주세요. 총 3개까지만 선택이 가능합니다.<br>
											음식 카테고리 선택:
											<select name="foodGroup" id="foodGroup">
											  <option value="감자 및 전분류" selected="selected">감자 및 전분류</option>
											  <option value="견과류">견과류</option>
											  <option value="곡류 및 그 제품">곡류 및 그 제품</option>
											  <option value="당류 및 그 제품">당류 및 그 제품</option>
											  <option value="두류 및 그 제품">두류 및 그 제품</option>
											  <option value="채소류">채소류</option>
											</select>
											<input type="text" class="form-control" name="foodName" id="foodName" placeholder="ex)좁쌀,메밀">
											<input type="button" class="btn btn-sm btn-primary" value="음식 검색하기" onclick="checkFoodInfo()">
											<div id="searchResult">
												<span id="foodSearchResult"></span><br>
												<select name="food" id="food">
													<option value="1" selected="selected">음식을 선택해주세요</option>
												</select>
												<input type="text" id="ingestionAmount" name="ingestionAmount" placeholder="섭취량을 적어주세요">
												<input type="button" class="btn btn-sm btn-primary" onclick="foodAddBtn()" value="음식추가하기">
											</div>
										</div>									
									<!-- 운동피드백 -->
									<input type="button" class="btn btn-sm btn-primary pull-right" onclick="exerciseFeedbackBtn()" value="운동피드백 등록하기">
									<div id="exerciseFeedback">
										<div><br>
											운동을 선택해주세요. 총 3개까지만 선택이 가능합니다.<br>
											운동 선택:
											<select name="exercise" id="exercise">
												<option value="1" selected="selected">운동을 선택해주세요</option>
											</select>
											<input type="text" class="form-control" id="exerciseTime" name="exerciseTime" placeholder="운동량을 적어주세요/min">
											<input type="button" class="btn btn-xs btn-default" onclick="exerciseAddBtn()" value="운동 추가하기">
										</div>
									</div>
									<!-- 운동 피드백 시작 -->
									<form class="form-inline">
										 <div class="form-group">
											<div id="exerciseSelect"></div>
										</div>
									</form>
									<form class="form-inline">
										<div class="form-group">
										<!-- 음식 피드백 시작 -->
											<div id="foodSelect"></div>
										</div>
									</form>
								</div>
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
	</div>
</body>
</html>