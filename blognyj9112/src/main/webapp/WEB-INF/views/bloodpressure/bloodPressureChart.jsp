<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>bloodPressureChart</title>
<jsp:include page="../include/header.jsp"></jsp:include>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery.min.js"></script>
<!-- google charts -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>

    $(document).ready(function(){
	    ajaxData();
	    $('#addBtn').hide();
	});	
	//ajax 실행
	function ajaxData() {
		var memberNo = $('#memberNoSession').val();
		console.log(memberNo);
		var request = $.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/bloodPressureChart?memberNo="+memberNo
        });   
	//ajax 실행 값 확인
	request.done(function( msg ) {
		//받아온 데이터 값 확인. 
		console.log(msg);
		if(msg.length<2){
			$('#chartResult').html('최근 한달동안 등록된 혈압이 2개 미만입니다. 평균 혈압 차트를 보고싶으시면 혈압을 더 등록해주세요.');
			$('#addBtn').show();
			$('#bloodPressureResult').hide();
			return false;
		}else if(msg.length<1){
			$('#chartResult').html('최근 한달동안 등록된 데이터가 없습니다.');
			$('#addBtn').show();
			$('#bloodPressureResult').hide();
			return false;
		}else{
			$('#addBtn').hide();
		}
		//구글 차트
		google.charts.load('current', {'packages':['line']}); //차트 스타일
		google.charts.setOnLoadCallback(drawChart);
		var chartDateformat = 'yy년MM월dd일';
		var chartLineCount = 10;
			function drawChart() {
				//배열값 확인.
				if(msg!=undefined && Array.isArray(msg)){
					console.log('array 확인');
					console.log(msg);
					console.log(msg.length);

					for(var i=0; i < msg.length; i++){
					console.log("1:"+msg[i].systolicPressure);
					console.log("2:"+msg[i].diastolicPressure);
					}
				}else{
					console.log('데이터 없음');
					console.log(msg);
				}
				var data = new google.visualization.DataTable();
				//하단의 등록일을 표시해 줄 컬럼
				data.addColumn('datetime', '등록일[day]');
				//데이터값(그래프 수치)
				data.addColumn('number', '수축기혈압');  
				data.addColumn('number', '이완기혈압');

				//열 추가 (컬럼 등록순으로 추가 등록일,수축기혈압,이완기혈압)
				for(var i=0; i < msg.length; i++){
					data.addRow([new Date(msg[i].bloodPressureDate),parseInt(msg[i].systolicPressure),parseInt(msg[i].diastolicPressure)]);
				}
				//그래프 옵션 설정
				var options = {
					//차트 상단의 제목 설정
					chart:{title : '혈압 그래프',
						subtitle: '최근 한달 동안의 혈압 수치 그래프'
					},
					//차트 크기 설정
					width: 900, // 넓이 옵션
					height: 500, // 높이 옵션
					//차트의 양옆 y축 설정 series, 라벨 axes ※3개 이상의 데이터값을 넣어줄 경우 겹치기때문에 제거..
                      series: {
                    	  0: {axis: 'diastolicPressure'},
                    	  1: {axis: 'systolicPressure'}
						},
					axes: {
						y: {
							diastolicPressure: {label: '이완기혈압[diastolicPressure]'},
							systolicPressure: {label: '수축기혈압[systolicPressure]'}
						}
					},
                      hAxis: {
						format: chartDateformat, 
						gridlines:{
							count:chartLineCount,
							units: {                      
								years : {format: ['yyyy년']},
								months: {format: ['MM월']},
								days  : {format: ['dd일']},
								hours : {format: ['HH시']}
								}
							}
						}
					};
				  //입력값을 화면에 뿌려주는 역할.
			      var chart = new google.charts.Line(document.getElementById('linechart_material'));
			      chart.draw(data, google.charts.Line.convertOptions(options));
            	}
			if(msg.length> 0){
	    		// 이완기,수축기 배열 평균 함수
	    		var arrLength = msg.length;
	    		function average(msg) {
	    		  var diaresult = 0;
	    		  for (var i = 0; i < arrLength; i++){
	    			  diaresult += msg[i].diastolicPressure;
	    		  }
	    		    return diaresult/arrLength;
	    		}
	    		function averages(msg) {
	      		  var sysresult = 0;
	      		  for (var i = 0; i < arrLength; i++){
	      			sysresult += msg[i].systolicPressure;
	      		  }
	    		    return sysresult/arrLength;
	      		}
	    		console.log("평균값:"+Math.round(average(msg))+","+Math.round(averages(msg)));
	    		$('#diastolicPressure').html('이완기 혈압의 평균은<span id="purple"> '+Math.round(average(msg))+'mmHg</span>입니다');
	    		$('#systolicPressure').html('수축기 혈압의 평균은<span id="purple"> '+Math.round(averages(msg))+'mmHg</span>입니다');
	    		/*
	    		평균치 고혈압,저혈압 체크. 
	    		수축기 혈압(최고혈압)이 140 mmHg 이상이거나 이완기 혈압(확장기 혈압 혹은 최저혈압)이 90 mmHg 이상
	    		정상	120 미만	80미만
	    		고혈압 전단계 120-139	80-89
	    		1기	140-159	90-99
	    		2기	160 또는 그 이상	100 또는 그 이상
	    		*/
	    		var diastolic = Math.round(average(msg));
				var systolic = Math.round(averages(msg));
				if(90<systolic){
					if(systolic<120){
						$('#systolicresult').html('혈압이 정상입니다.');
					}else if(systolic<140){
						$('#systolicresult').html('고혈압이 의심됩니다. 병원을 방문해주세요.');
					}else if(119<systolic){
						$('#systolicresult').html('고혈압 전 단계입니다. 병원을 방문해주세요.');
					}
				}else{
					$('#systolicresult').html('저혈압이 의심됩니다.');
				}
				
				if(60<diastolic){
					if(diastolic<80){
						$('#diastolicresult').html('정상입니다.');
					}else if(diastolic<90){
						$('#diastolicresult').html('고혈압이 의심됩니다. 병원을 방문해주세요.');
					}else if(79<diastolic){
						$('#diastolicresult').html('고혈압 전 단계입니다. 병원을 방문해주세요.');
					}
				}else{
					$('#diastolicresult').html('저혈압이 의심됩니다.');
				}
			}
	});

	request.fail(function( jqXHR, textStatus ) {
		  alert( "Request failed: " + textStatus );
		});
	}
	function returnBtn(){
		history.back();
	}
</script>
<style type="text/css">
tr {
text-align: center;
font-size: 12px;
}
#purple{
color: #9c27b0;
 font-weight: bold;
}
h5{
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
			<h4>최근 한달간의 혈압 그래프</h4>
				<input type="hidden" id="memberNoSession" value="${sessionScope.memberSessionNo}">
				<span id="chartResult"></span>
				<div id="addBtn">
					<a href="${pageContext.request.contextPath}/addBloodPressure">혈압 등록하기</a>
				</div>
				<div class="col-sm-12" align="center">
				<div class="row">
					<div class='col-sm-8'>
						<!-- 차트가 그려지는 위치 -->
						<div id="linechart_material"></div>
						</div>
				 	<div class='col-sm-3' align="center">			 	
						<table class="table table-hober">
							<tr>
								<th>단계</th>
								<td>수축기</td>
								<td>이완기</td>
							</tr>
							<tr>
								<th>정상 혈압</th>
								<td>120mmHg 미만</td>
								<td>80mmHg미만</td>
							</tr>
							<tr>
								<th>고혈압 전(前)단계</th>
								<td>120-139mmHg</td>
								<td>80-89mmHg</td>
							</tr>
							<tr>
								<th>1기 고혈압</th>	
								<td>140-159mmHg</td>
								<td>90-99mmHg</td>
							</tr>
							<tr>
								<th>2기고혈압</th>	
								<td>160mmHg 이상</td>
								<td>100mmHg 이상</td>
							<tr>
						</table>
						<!-- 혈압 결과값 계산 -->
						<div id="bloodPressureResult">
						<h5>최근 한달간의 혈압 분석 결과</h5>
							<div>
								<span id="purple">수축기 혈압 :</span> 
								<span id="systolicPressure"></span>
								<div>
								<span id="systolicresult"></span>
								</div>
							</div>
							<div>
								<span id="purple">이완기 혈압 :</span>
								<span id="diastolicPressure"></span>
								<div>
								<span id="diastolicresult"></span>
								</div>
							</div>
					 	</div>
						<div class="navbar-form navbar-right">
							<input type="button" class="btn btn-primary" onclick="returnBtn()" value="돌아가기">
						</div>
					</div>
				</div>
 			</div>
 			</div>
		</div>
	</div>
</body>
</html>