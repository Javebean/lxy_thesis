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
               请输入留言信息：
            </h4>
         </div>
         <div class="modal-body">
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
var items =3;
var stu = getCookie("user");
var delete_but = function(source){
	$(source).click(function(){
		var de_id = $(source).attr("de_id");
		$.get("lxy/delestuissue",{stu_num:stu,issue_id:de_id},function(data){
			if(data){
				window.location.reload();
			}else{
				alert("取消失败");
			}
		});
	});
}


var sub_mes = function(source){
	$(source).click(function(){
		$("#sub_mes").click(function(){
			var teaNum = $(source).attr("teaNum");
			var content = $("#mes_content").val();
			var param = {"stu_id":stu,"tea_id":teaNum,"content":$.trim(content)};
			$.getJSON("lxy/addmess2tea",param,function(data){
				if(data){
					alert("留言成功");
					window.location.reload();
				}else{
					alert("提交失败");
				}
			});
		});
	});
}


var mypopover = function(tea_num,div_id){
	$.get("lxy/getteabynum/"+tea_num,function(data){
		var html = '';
		var keyArr = ['老师编号','姓名','性别','电话','email','职称','老师介绍'];
		var index = 0;
		for(var item in data){
			if(item!="id" && item!="password"){
				html+= '<p>'+keyArr[index++]+':'+data[item]+'</p><br>';
			}
		}
		$("#"+div_id).html(html);
	});
	
	return '<div id="'+div_id+'">Loading...</div>';
}



var loadMessages = function(start){
	$.getJSON("lxy/getstuissues/"+stu,function(data){
		$("tbody.abstract").empty();
		$.each(data,function(index){
			var stateStr;
			var sta = this.state;
			if(sta==0){
				stateStr = "未审核";
			}else if(sta==1){
				stateStr = "通过";
			}else if(sta==2){
				stateStr = "未通过";
			}
			
			
			var html = '<tr>'
						+'<td>'+this.i_name+'</td>'
						+'<td>'+this.i_teacher+'<a tabindex="'+index+'" data-trigger="focus" tea_num="'+this.tea_num+'" title="老师信息" class="pp" data-container="body" data-toggle="popover" data-placement="bottom" data-content="">[详细]</a></td>'
						+'<td>'+this.limit_pro+'</td>'
						+'<td>'+stateStr+'</td>'
						+'<td>'
							+'<button type="button" class="btn btn-danger" id="delete_but" de_id="'+this.id+'">取消选择</button>'
							+'&nbsp;'
							+'<button type="button" class="btn btn-info mes_but" teaNum="'+this.tea_num+'" data-toggle="modal" data-target="#myModal">留言</button>'
						+'</td>'
						+'</tr>';
			$("tbody.abstract").append(html);
			var but = $("tbody.abstract").children().last().find("#delete_but");
			delete_but(but);
			
			//留言
			var but2 = $("tbody.abstract").children().last().find(".mes_but");
			sub_mes(but2);
			
			var pp = $("tbody.abstract").children().last().find("a.pp");
			$(pp).popover({
				"html":true,
				"content":function(){
					var div_id = "tmp_id"+$.now();
					return mypopover($(this).attr("tea_num"),div_id);
				}
			});
			
			
		});
		
	});
	
}
	 $(function(){
		 loadMessages(1);
	})
</script>

</body>
</html>