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
	//ajax ����
	function ajaxData() {
		var memberNo = $('#memberNoSession').val();
		console.log(memberNo);
		var request = $.ajax({
		type : "POST",
		url : "${pageContext.request.contextPath}/bloodPressureChart?memberNo="+memberNo
        });   
	//ajax ���� �� Ȯ��
	request.done(function( msg ) {
		//�޾ƿ� ������ �� Ȯ��. 
		console.log(msg);
		if(msg.length<2){
			$('#chartResult').html('�ֱ� �Ѵ޵��� ��ϵ� ������ 2�� �̸��Դϴ�. ��� ���� ��Ʈ�� ��������ø� ������ �� ������ּ���.');
			$('#addBtn').show();
			$('#bloodPressureResult').hide();
			return false;
		}else if(msg.length<1){
			$('#chartResult').html('�ֱ� �Ѵ޵��� ��ϵ� �����Ͱ� �����ϴ�.');
			$('#addBtn').show();
			$('#bloodPressureResult').hide();
			return false;
		}else{
			$('#addBtn').hide();
		}
		//���� ��Ʈ
		google.charts.load('current', {'packages':['line']}); //��Ʈ ��Ÿ��
		google.charts.setOnLoadCallback(drawChart);
		var chartDateformat = 'yy��MM��dd��';
		var chartLineCount = 10;
			function drawChart() {
				//�迭�� Ȯ��.
				if(msg!=undefined && Array.isArray(msg)){
					console.log('array Ȯ��');
					console.log(msg);
					console.log(msg.length);

					for(var i=0; i < msg.length; i++){
					console.log("1:"+msg[i].systolicPressure);
					console.log("2:"+msg[i].diastolicPressure);
					}
				}else{
					console.log('������ ����');
					console.log(msg);
				}
				var data = new google.visualization.DataTable();
				//�ϴ��� ������� ǥ���� �� �÷�
				data.addColumn('datetime', '�����[day]');
				//�����Ͱ�(�׷��� ��ġ)
				data.addColumn('number', '���������');  
				data.addColumn('number', '�̿ϱ�����');

				//�� �߰� (�÷� ��ϼ����� �߰� �����,���������,�̿ϱ�����)
				for(var i=0; i < msg.length; i++){
					data.addRow([new Date(msg[i].bloodPressureDate),parseInt(msg[i].systolicPressure),parseInt(msg[i].diastolicPressure)]);
				}
				//�׷��� �ɼ� ����
				var options = {
					//��Ʈ ����� ���� ����
					chart:{title : '���� �׷���',
						subtitle: '�ֱ� �Ѵ� ������ ���� ��ġ �׷���'
					},
					//��Ʈ ũ�� ����
					width: 900, // ���� �ɼ�
					height: 500, // ���� �ɼ�
					//��Ʈ�� �翷 y�� ���� series, �� axes ��3�� �̻��� �����Ͱ��� �־��� ��� ��ġ�⶧���� ����..
                      series: {
                    	  0: {axis: 'diastolicPressure'},
                    	  1: {axis: 'systolicPressure'}
						},
					axes: {
						y: {
							diastolicPressure: {label: '�̿ϱ�����[diastolicPressure]'},
							systolicPressure: {label: '���������[systolicPressure]'}
						}
					},
                      hAxis: {
						format: chartDateformat, 
						gridlines:{
							count:chartLineCount,
							units: {                      
								years : {format: ['yyyy��']},
								months: {format: ['MM��']},
								days  : {format: ['dd��']},
								hours : {format: ['HH��']}
								}
							}
						}
					};
				  //�Է°��� ȭ�鿡 �ѷ��ִ� ����.
			      var chart = new google.charts.Line(document.getElementById('linechart_material'));
			      chart.draw(data, google.charts.Line.convertOptions(options));
            	}
			if(msg.length> 0){
	    		// �̿ϱ�,����� �迭 ��� �Լ�
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
	    		console.log("��հ�:"+Math.round(average(msg))+","+Math.round(averages(msg)));
	    		$('#diastolicPressure').html('�̿ϱ� ������ �����<span id="purple"> '+Math.round(average(msg))+'mmHg</span>�Դϴ�');
	    		$('#systolicPressure').html('����� ������ �����<span id="purple"> '+Math.round(averages(msg))+'mmHg</span>�Դϴ�');
	    		/*
	    		���ġ ������,������ üũ. 
	    		����� ����(�ְ�����)�� 140 mmHg �̻��̰ų� �̿ϱ� ����(Ȯ��� ���� Ȥ�� ��������)�� 90 mmHg �̻�
	    		����	120 �̸�	80�̸�
	    		������ ���ܰ� 120-139	80-89
	    		1��	140-159	90-99
	    		2��	160 �Ǵ� �� �̻�	100 �Ǵ� �� �̻�
	    		*/
	    		var diastolic = Math.round(average(msg));
				var systolic = Math.round(averages(msg));
				if(90<systolic){
					if(systolic<120){
						$('#systolicresult').html('������ �����Դϴ�.');
					}else if(systolic<140){
						$('#systolicresult').html('�������� �ǽɵ˴ϴ�. ������ �湮���ּ���.');
					}else if(119<systolic){
						$('#systolicresult').html('������ �� �ܰ��Դϴ�. ������ �湮���ּ���.');
					}
				}else{
					$('#systolicresult').html('�������� �ǽɵ˴ϴ�.');
				}
				
				if(60<diastolic){
					if(diastolic<80){
						$('#diastolicresult').html('�����Դϴ�.');
					}else if(diastolic<90){
						$('#diastolicresult').html('�������� �ǽɵ˴ϴ�. ������ �湮���ּ���.');
					}else if(79<diastolic){
						$('#diastolicresult').html('������ �� �ܰ��Դϴ�. ������ �湮���ּ���.');
					}
				}else{
					$('#diastolicresult').html('�������� �ǽɵ˴ϴ�.');
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
			<h4>�ֱ� �Ѵް��� ���� �׷���</h4>
				<input type="hidden" id="memberNoSession" value="${sessionScope.memberSessionNo}">
				<span id="chartResult"></span>
				<div id="addBtn">
					<a href="${pageContext.request.contextPath}/addBloodPressure">���� ����ϱ�</a>
				</div>
				<div class="col-sm-12" align="center">
				<div class="row">
					<div class='col-sm-8'>
						<!-- ��Ʈ�� �׷����� ��ġ -->
						<div id="linechart_material"></div>
						</div>
				 	<div class='col-sm-3' align="center">			 	
						<table class="table table-hober">
							<tr>
								<th>�ܰ�</th>
								<td>�����</td>
								<td>�̿ϱ�</td>
							</tr>
							<tr>
								<th>���� ����</th>
								<td>120mmHg �̸�</td>
								<td>80mmHg�̸�</td>
							</tr>
							<tr>
								<th>������ ��(��)�ܰ�</th>
								<td>120-139mmHg</td>
								<td>80-89mmHg</td>
							</tr>
							<tr>
								<th>1�� ������</th>	
								<td>140-159mmHg</td>
								<td>90-99mmHg</td>
							</tr>
							<tr>
								<th>2�������</th>	
								<td>160mmHg �̻�</td>
								<td>100mmHg �̻�</td>
							<tr>
						</table>
						<!-- ���� ����� ��� -->
						<div id="bloodPressureResult">
						<h5>�ֱ� �Ѵް��� ���� �м� ���</h5>
							<div>
								<span id="purple">����� ���� :</span> 
								<span id="systolicPressure"></span>
								<div>
								<span id="systolicresult"></span>
								</div>
							</div>
							<div>
								<span id="purple">�̿ϱ� ���� :</span>
								<span id="diastolicPressure"></span>
								<div>
								<span id="diastolicresult"></span>
								</div>
							</div>
					 	</div>
						<div class="navbar-form navbar-right">
							<input type="button" class="btn btn-primary" onclick="returnBtn()" value="���ư���">
						</div>
					</div>
				</div>
 			</div>
 			</div>
		</div>
	</div>
</body>
</html>