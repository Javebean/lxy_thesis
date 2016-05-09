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

				<h2 class="sub-header">学生留言</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>留言名称</th>
								<th>留言学生</th>
								<th>留言时间</th>
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
	
	
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               回复留言
            </h4>
         </div>
         <div class="modal-body">
         	<div class="panel panel-default">
   				<div class="panel-body">
      				from:<span id="from" ></span>&nbsp;&nbsp;&nbsp;     时间：<span id="m_time"></span>
   				</div>
   				<div class="panel-body">
      			留言内容：<div id="m_content"></div>
   				</div>
			</div>
         
         
           <textarea class="form-control" rows="2" id="mes_content">
           </textarea>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="sub_mes">
               提交
            </button>
         </div>
      </div><!-- /.modal-content -->
</div>
</div>	
	
	

<script type="text/javascript">
/*public function*/
var delete_but = function(source){
	$(source).click(function(){
		var de_id = $(source).attr("de_id");
		deleteItem("lxy/deletemesbyid",de_id);
	});
}

//展示留言的相关信息
var showMessage = function(source){
	$(source).click(function(){
		var from = $(source).attr("from");
		var content = $(source).attr("content");
		var m_time = $(source).attr("time");
		$("#from").text(from);
		$("#m_time").text(m_time);
		$("#m_content").text(content);
		$("#sub_mes").click(function(){
			var t_id = $(source).attr("t_id");
			var mes_content  = $("#mes_content").val();
			$.getJSON("lxy/teareply2stu/"+t_id,{"reply":$.trim(mes_content)},function(data){
				if(data){
					alert("回复成功");
					window.location.reload();
				}else{
					alert("回复失败");
				}
			})
		});
	});
	
}



var loadMessages = function(){
	var tea_num = getCookie("user");
	$.getJSON("lxy/getallmessagebytnum/"+tea_num,function(data){
		$("tbody.abstract").empty();
		$.each(data,function(){
			var html = '<tr>'
						+'<td>'+this.content+'</td>'
						+'<td>'+this.stu_name+'</td>'
						+'<td>'+this.m_time+'</td>'
						+'<td>'
							+'<a type="button" class="btn btn-success reply"  t_id ="'+this.id+'" from="'+this.stu_name+'" content="'+this.content+'" time="'+this.m_time+'"    data-toggle="modal" data-target="#myModal">回复</a>'
							+'<button type="button" class="btn btn-danger" id="delete_but" de_id="'+this.id+'">删除</button>'
						+'</td>'
						+'</tr>';
			$("tbody.abstract").append(html);
			var but = $("tbody.abstract").children().last().find("#delete_but");
			delete_but(but);
			
			var but2 = $("tbody.abstract").children().last().find(".reply");
			showMessage(but2);
		});
		
	});
	
}
	 $(function(){
		 loadMessages();
	})
</script>

</body>
</html>