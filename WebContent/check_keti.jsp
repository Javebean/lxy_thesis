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

				<h2 class="sub-header">选题审核</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>课题名称</th>
								<th>学生学号</th>
								<th>学生姓名</th>
								<th>性别</th>
								<th>专业</th>
								<th>状态</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody class="abstract">
						
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
/*public function*/
var delete_but = function(source){
	$(source).click(function(){
		var de_id = $(source).attr("de_id");
		deleteItem("lxy/deleteStudent_issuebyId",de_id);
	});
}

var agree_but = function(source){
	$(source).click(function(){
		var de_id = $(source).attr("de_id");
		$.get("lxy/agreestudent_issue/"+de_id,function(data){
			if(data){
				window.location.reload();
			}
		});
	});
}

var mypopover = function(source){
	$(source).on('inserted.bs.popover', function () {
		var stu_num = $(this).attr("stu_num");
		$.get("lxy/getdetailstu",{"stu_num":stu_num},function(data){
			var html = '';
			var keyArr = ['姓名','学号','性别','专业','年级','班级','生日','身份证','电话','email'];
			var index = 0;
			for(var item in data){
				if(item!="id" && item!="password"){
					html+= '<p>'+keyArr[index++]+':'+data[item]+'</p><br>';
				}
			}
			$(source).attr("data-content","姓名:顿6666");
			console.log($(source).attr("data-content"));
			$(source).popover('toggle');
		});
		
	});
}




var loadMessages = function(){
	var tea_num = getCookie("user");
	$.getJSON("lxy/getcheckissue/"+tea_num,function(data){
			$("tbody.abstract").empty();
			$.each(data,function(index){
				var html = '<tr>'
							+'<td>'+this.iss_name+'</td>'
							+'<td>'+this.stu_num+'</td>'
							+'<td>'+this.stu_name+'<a tabindex="'+index+'" data-trigger="focus" stu_num="'+this.stu_num+'" title="学生信息" class="pp" data-container="body" data-toggle="popover" data-placement="bottom" data-content="">[详细]</a>'
							+'<td>'+this.stu_sex+'</td>'
							+'<td>'+this.stu_pro+'</td>'
							+'<td>'+this.state+'</td>'
							+'<td>'
								+'<button type="button" class="btn btn-success" id="agree_but" de_id="'+this.id+'">通过</button>'
								+'<button type="button" class="btn btn-danger" id="delete_but" de_id="'+this.id+'">不通过</button>'
							+'</td>'
							+'</tr>';
				$("tbody.abstract").append(html);
				var but = $("tbody.abstract").children().last().find("#delete_but");
				var but1 = $("tbody.abstract").children().last().find("#agree_but");
				var pp = $("tbody.abstract").children().last().find(".pp");
				delete_but(but);
				agree_but(but1);
				mypopover(pp);
			});
			
			 $("[data-toggle='popover']").popover('hide');
	});
}
	 $(function(){
		 loadMessages();
	})
</script>

</body>
</html>