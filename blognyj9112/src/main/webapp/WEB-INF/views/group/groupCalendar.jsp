<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캘린더</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.1/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/3.9.0/fullcalendar.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar-scheduler/1.9.4/scheduler.css">
<script type="text/javascript" src="https://fullcalendar.io/releases/fullcalendar-scheduler/1.9.4/scheduler.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	  ajaxData(); 
});

function ajaxData() {
	var groupName = $('#groupName').val();
	var request = $.ajax({
	type : "POST",
	url : "${pageContext.request.contextPath}/groupCalendarList?groupName="+groupName
	});   
	//ajax 실행 값 확인
	request.done(function(msg) {
		console.log(msg);
		var medication = msg.groupCalendarMedication; //복약일정
		var treatment = msg.groupCalendarTreatment; //진료일정
		var memberlist = msg.groupRelationMember; //회원리스트
		var creationMember = msg.creationMember; //그룹장 정보
		var healthScreen = msg.groupCalendarHealthScreen; //건강검진
		var healthSurvey = msg.groupCalendarHealthSurvey; //건강설문
		if(treatment!=undefined && Array.isArray(treatment)){
			console.log('array 확인');
			console.log("진료리스트:"+treatment+treatment.length);
			console.log("복약스트:"+medication+medication.length);
			console.log("회원리스트:"+memberlist+memberlist.length);
			console.log("건강검진:"+healthScreen+healthScreen.length);
			console.log("건강설문:"+healthSurvey+healthSurvey.length);
			console.log("그룹장:"+creationMember);
			//회원리스트
			var resource = [{ id: creationMember.memberNo, title: creationMember.memberName }];
	 		for(var i = 0; i<memberlist.length; i++){
				resource[i+1] = { id: memberlist[i].memberId, title: memberlist[i].memberName}
			} 
	 		//복약일정 
	 		var test = [];
	 		if(medication.length>0){
	 	 		for(var i = 0; i<medication.length; i++){
	 	 			test.push({
	 	 				id : medication[i].memberId
	 					,title: '[복약]'+medication[i].medicationTitle+' [하루'+medication[i].dosage+'번]'
	 					,resourceIds: medication[i].memberId
	 					,start: medication[i].medicationStartDate
	 					,end: medication[i].medicationEndDate
	 					,color: '#FF5E00'
	 	 			})
	 	 		}
	 		}
	 		console.log('test:'+test);
			//진료일정
	 		if(treatment.length>0){
		 	 	for(var i = 0; i<treatment.length; i++){
		 	 		test.push( {
		 	 			id:treatment[i].memberId
		 				,title: '[진료]'+treatment[i].memberName+'['+treatment[i].treatmentTitle+']'
		 				,resourceIds: treatment[i].memberId
		 				,start: treatment[i].tratmentDate
		 				,color: '#0100FF'
		 	 		})
				}
		 	 }
			//건강검진
			if(healthScreen.length>0){
	 	 		for(var i = 0; i<healthScreen.length; i++){
	 	 			test.push( {
	 	 				id: healthScreen[i].memberId
	 					,title: '[건강검진]'+healthScreen[i].memberName
	 					,resourceIds: healthScreen[i].memberId
	 					,start: healthScreen[i].healthScreenDate
	 					,color: '#E5D85C'
	 	 			})
	 			}
			}
			//건강설문
			if(healthSurvey.length>0){
	 	 		for(var i = 0; i<healthSurvey.length; i++){
	 	 			test.push( {
	 	 				id: healthSurvey[i].memberId
	 					,title: '[건강설문]'+healthSurvey[i].memberName+'['+healthSurvey[i].healthSurveyEvaluationDo+']'
	 					,resourceIds: healthSurvey[i].memberId
	 					,start: healthSurvey[i].healthSurveyResultDate
	 					,color: '#6B9900'
	 	 			})
	 			}
			}
	 		console.log('test:'+test);
	 		console.log('test:'+test.length);
	 		console.log('resource:'+resource.length);
	 		
		}else{
			console.log('데이터 없음');
		}
		//캘린더
		  $('#calendardaily').fullCalendar({
				header: {
			    	left: 'today prev,next',
			        center: 'title', 
			        right: 'month,timelineDay,listWeek'
			    },
			    defaultView: 'month',
			    defaultDate : new Date(),
			    navLinks: true,
			    eventLimit: true,
			     
			    views: { 
			    	month:{
			    		buttonText: 'month',
			    		eventLimit : 3
			    	},
			    	timelineDay:{ 
			    		buttonText: '오늘 일정 보기',
			    		slotLabelFormat : ['H:mm'],
					},
					listWeek: {
						buttonText: '일주일 일정 보기',
						type: 'timeline',
						duration: { days: 7 },
						slotLabelFormat : ['ddd D/M']
					}
				},	
				events : test,
				resourceLabelText: '그룹회원명', 
				resources: resource
			});
	});

	request.fail(function( jqXHR, textStatus ) {
		alert( "Request failed: " + textStatus );
	});
} 


</script>

<style> 

body {
margin: 40px 10px; 
padding: 0; 
font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif; 
font-size: 15px; 
} 
#calendar {
 max-width: 500px; margin: 0 auto; 
 } 
</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
				<div align="center">
					<div class="btn-group" role="group" aria-label="...">
						<input type="hidden" id="groupName" value="${groupName}">
						<input type="button" onclick="groupDetail()" class="btn btn-sm btn-primary" value="그룹상세">
						<button onclick="groupCalendar()" class="btn btn-sm btn-primary">그룹캘린더</button>
						<button onclick="groupRelation()" class="btn btn-sm btn-primary">그룹관계도</button>
						<button onclick="groupMemberList()" class="btn btn-sm btn-primary">회원리스트</button>
						<c:if test="${result >0 }">
							<button type="button" onclick="groupMemberInvite()" class="btn btn-sm btn-primary">회원초대하기</button>
							<button type="button" onclick="inviteGroupMemberList()" class="btn btn-sm btn-primary">그룹에초대한멤버</button>
						</c:if>
						<button onclick="groupMain()" class="btn btn-sm btn-primary">그룹메인으로</button>
					</div>
				</div>
					<div class="row">
					<div class="col-md-2">
					</div>
					<div class="col-md-8">
					<div id="calendardaily"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>