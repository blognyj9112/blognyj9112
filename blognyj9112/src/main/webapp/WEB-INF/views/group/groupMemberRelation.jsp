<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groupMemberRelation</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
 var groupName= "groupName7";
 $(document).ready(function(){
	    ajaxData();
	});	
	function ajaxData() {

		var request = $.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/groupMemberRelationChart?groupName="+groupName
        });   
	//ajax 실행 값 확인
	request.done(function( msg ) {
		//받아온 데이터 값 확인. 
		console.log(msg);

	google.charts.load('current', {packages:["orgchart"]});
	google.charts.setOnLoadCallback(drawChart);
	function drawChart() {
		var createMemb = msg.createMemb;
		var relationMember = msg.groupRelationMember;
		if(relationMember!=undefined && Array.isArray(relationMember)){
			console.log('array 확인');
			console.log(relationMember);
			console.log(relationMember.length);
			for(var i=0; i < relationMember.length; i++){
			console.log("memberId:"+relationMember[i].memberId);
			}
		}else{
			console.log('데이터 없음');
			console.log(msg);
		}
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Name');//하위그룹
    data.addColumn('string', 'Manager');//상위그룹
    data.addColumn('string', 'ToolTip');
    data.addRow([{v:createMemb , f: '<p>'+createMemb+'</p>'+'<div style=" color:#5D5D5D; font-style:bold">그룹장</div>'},'','그룹장']);
    data.addRow([{v:relationMember[0].memberId , f: '<p>'+relationMember[0].memberName+'</p>'+'<div style=" color:#5D5D5D; font-style:bold">그룹원</div>'},createMemb,'']);
    data.addRow([{v:relationMember[1].memberId , f: '<p>'+relationMember[1].memberName+'</p>'+'<div style=" color:#5D5D5D; font-style:bold">그룹원</div>'},createMemb,'']);
     for(var i=2; i < relationMember.length; i++){
    	 data.addRow([{v:relationMember[i].memberId, f: '<p>'+relationMember[i].memberName+'</p>'+'<div style=" color:#5D5D5D; font-style:bold">그룹원</div>'},relationMember[i-1].memberId,'']);
    	 data.addRow([{v:relationMember[1].memberId , f: '<p>'+relationMember[1].memberName+'</p>'+'<div style=" color:#5D5D5D; font-style:bold">그룹원</div>'},relationMember[i-2].memberId,'']);
 	} //v: 연결되는 아이디 값. f: 화면에 보여지는 부분, ['하위그룹','상위그룹Id','']
 	console.log(data);
	
    // Create the chart.
    var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
    // Draw the chart, setting the allowHtml option to true for the tooltips.
    chart.draw(data, {allowHtml:true,nodeClass:'maman'});
	}
	});

	request.fail(function( jqXHR, textStatus ) {
		  alert( "Request failed: " + textStatus );
		});
  }

</script>
<style type="text/css">
.maman {
	background : linear-gradient( to bottom, lightgray, white );
	color: black;
	width: 150px;
	height: 80px;
	text-align: center;
	border-radius: 5px;
	font-size: 2.5em;
/* 	border-width: 1px 20px 1px 20px; */
}
div {
    font-size: 15px;
}
p {
    font-size: 24px;
}
.google-visualization-orgchart-lineleft {
	border-left: 1px solid #333!important;
}
.google-visualization-orgchart-linebottom {
	border-bottom: 1px solid #333!important;
}
.google-visualization-orgchart-lineright {
	border-right: 1px solid #333!important;
}
</style>
</head>
<body>
	<div class="sidebar-wrapper">
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-panel">
			<jsp:include page="../include/top.jsp"></jsp:include>
			<div class="content">
				<h1>그룹 관계도</h1>
					<!-- 관계도 위치 -->
					<div id="chart_div"></div>
			</div>
		</div>
	</div>
</body>
</html>