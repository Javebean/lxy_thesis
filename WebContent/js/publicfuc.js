/**
 * 
 */
var setCookie = function (name,value)
{
    var exp = new Date();
    exp.setTime(exp.getTime() + 60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}


var getCookie = function (name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");

    if(arr=document.cookie.match(reg))

        return unescape(arr[2]);
    else
        return null;
}

var deleteCookie = function(name,value){
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	
}

var deleteItem = function (url,dId){
	$.ajax({
		url:url+"/"+dId,
		type:"get",
		dataType:"json",
		success:function (data){
			if(data){
				window.location.reload();
			}else{
				alert("删除失败！");
			}
		},
		error:function(){
			alert("删除失败！");
		} 
		
	});
}




//分页按钮
var pagebutton = function(pageNumUrl,items){
	var curpage =1;
	$(".pageButton").click(function(){
		//再去查一共多少页
		var name = $(this).attr("name");
		$.get(pageNumUrl,{"items":items},function(pageNums){
			if(name=="sy"){
				curpage = 1;
			}else if(name=="syy"){
				curpage = --curpage>1?curpage:1;
			}else if(name=="xyy"){
				curpage = ++curpage>pageNums?pageNums:curpage;
			}else if(name=="wy"){
				curpage=pageNums;
			}
			loadMessages(curpage);
			$("#showCurrnetPage").text(curpage+"/"+pageNums);
			
		});
	});
	
}

