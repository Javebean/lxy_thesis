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

				<h2 class="sub-header">管理课题</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>课题名称</th>
								<th>指导老师</th>
								<th>限选专业</th>
								<th>限选人数</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody class="abstract">
						
						</tbody>
					</table>
					<ul class="pager">
			 			<li><a class ="pageButton hand-point" name="sy">首页</a></li>
			 			<li><a class ="pageButton hand-point" name="syy">上一页</a></li>
			 			<li><a class ="pageButton hand-point" name="xyy">下一页</a></li>
			 			<li><a class ="pageButton hand-point" name="wy">尾页</a></li>
			 			<li><a class="hand-point">当前页码：<span id="showCurrnetPage" >1</span></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
/*public function*/
var items =10;
var stu = getCookie("user");
var delete_but = function(source){
	$(source).click(function(){
		var de_id = $(source).attr("de_id");
		var tea_num = $(source).attr("tea_num");
		$.get("lxy/stuselectissue",{stu_num:stu,issue_id:de_id,"tea_num":tea_num},function(data){
			console.log(data);
			if(data){
				window.location.reload();
			}
		});
	});
}




var loadMessages = function(start,arr){
	$.getJSON("lxy/getissuebypage/"+start+"/"+items,{stu_num:stu},function(data){
		$("tbody.abstract").empty();
		$.each(data,function(){
			if(this.select){
				butt = '<button type="button" class="btn btn-danger disabled" id="delete_but" de_id="'+this.id+'">已选择</button>'
			}else{
				butt = '<button type="button" class="btn btn-danger" id="delete_but" tea_num="'+this.tea_num+'" de_id="'+this.id+'">选择</button>'
			}
			var html = '<tr>'
						+'<td>'+this.i_name+'</td>'
						+'<td>'+this.i_teacher+'</td>'
						+'<td>'+this.limit_pro+'</td>'
						+'<td>'+this.limit_num+'</td>'
						+'<td>'
							+butt
						+'</td>'
						+'</tr>';
			$("tbody.abstract").append(html);
			var but = $("tbody.abstract").children().last().find("#delete_but");
			delete_but(but);
		});
		
	});
	
}
	 $(function(){
		 loadMessages(1);
		 pagebutton("lxy/getissuepages",items);
	})
</script>

</body>
</html>