<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>毕业论文在线选题管理系统</title>
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
</head>
<body id="loginbody">

<div id="loginIn">
	<table>
		<tr><td><h2>用户登陆</h2></td></tr>
		<tr>
			<td>
				<input class="form-control" name="username" type="text" placeholder="用户名">
			</td>
		</tr>
		<tr>
			<td>
				<input class="form-control" name="password" type ="password" placeholder="密码">
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<select class="form-control" name="role" id="role">
					<option value="0">管理员</option>
					<option value="1">教师</option>
					<option value="2">学生</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<button id="login" class="btn btn-lg btn-primary btn-block">登陆</button>
				<img alt="loading" src="images/loading.gif" class="hidden">
				<p class="hidden msg"></p>
			</td>
		</tr>
	</table>

</div>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/publicfuc.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$("#loginIn img").removeClass("hidden");
			$("#login").addClass("hidden");
			var role = $("#role").val();
			var username = $("input[name=username]").val();
			var param = {"username":username,"password":$("input[name=password]").val()};
			$.post("lxy/rolelogin/"+role,param,function(data0){
				//$(".msg").removeClass("hidden").text(result.msg);
				if(data0){
					console.log(data0);
					/*登陆成功*/
					setCookie("user", username);
					if(role==0){
						window.location.href="manager.jsp";
					}else if(role==1){
						window.location.href="tea_manager.jsp";
					}else if(role==2){
						window.location.href="stu_manager.jsp";
					}
				}else{
					alert("用户名或密码错误！");
				}
				
				$("#login").removeClass("hidden");
				$("#loginIn img").addClass("hidden");
			});
		});
	})

</script>
</body>
</html>