<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<!-- <link rel="icon" href="../../favicon.ico"> -->

<title>毕业论文在线选题管理系统</title>
<%@include file="public/public.html"%>
<%@include file="public/public_1.html"%>

<link href="css/dashboard.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style id="style-1-cropbar-clipper">
.en-markup-crop-options {
	top: 18px !important;
	left: 50% !important;
	margin-left: -100px !important;
	width: 200px !important;
	border: 2px rgba(255, 255, 255, .38) solid !important;
	border-radius: 4px !important;
}

.en-markup-crop-options div div:first-of-type {
	margin-left: 0px !important;
}
</style>
</head>
<body>
	<%@include file="public/navbar.html"%>
	<div class="container-fluid">
		<div class="row">

			<%@include file="public/tea_nav.html"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">控制台</h1>

				<h2 class="sub-header">选题统计</h2>
				<div class="table-responsive">
					<div id="main" style="width: 50%;height:400px;"></div>
					
				</div>
			</div>
		</div>
	</div>

<script src="js/echarts.common.min.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

var getCountSelectByStu = function(){
	var tea_num = getCookie("user");
	var arr1 = [];
	var arr2 = [];
	$.getJSON("lxy/countissueselectbystu/"+tea_num,function(data){
		for(var item in data){
			arr1.push(item);
			arr2.push(data[item]);
		}
		
		// 使用刚指定的配置项和数据显示图表。
		// 指定图表的配置项和数据
		myChart.setOption({
		    title: {
		        text: '老师课题被选情况统计'
		    },
		    tooltip: {},
		    legend: {
		        data:['人数']
		    },
		    xAxis: {
		        data: arr1
		    },
		    yAxis: {},
		    series: [{
		        name: '人数',
		        type: 'bar',
		        data: arr2
		    }]
		});
		
		
		
	})
}

$(function(){
	getCountSelectByStu();
});


</script>
</body>
</html>