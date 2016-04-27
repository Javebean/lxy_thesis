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

			<%@include file="public/stu_nav.html"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">控制台</h1>

				<h2 class="sub-header">添加公告</h2>
				
				<form class="form-horizontal" role="form" id="add_stu_form">
				   <div class="form-group">
				      <label for="firstname" class="col-sm-2 control-label">标题</label>
				      <div class="col-sm-10">
				         <input type="text"  disabled="disabled"  name="title" class="form-control" placeholder="">
				      </div>
				   </div>
				   
				   <div class="form-group">
				      <label for="firstname" class="col-sm-2 control-label">内容</label>
				      <div class="col-sm-10">
				         <textarea  name="content" disabled="disabled" class="form-control" rows="5"></textarea>
				      </div>
				   </div>
				   
				   
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript">
var id = ${param.anno_id};

var getStuById = function(){
	$.get("lxy/getannobyid/"+id,function(data){
		for(var item in data){
			$(":input[name='"+item+"']").val(data[item]);
		}
	});
}

$(function(){
	getStuById();
});


</script>

</body>
</html>