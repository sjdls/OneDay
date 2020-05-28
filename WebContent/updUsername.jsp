<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

<script type="text/javascript">
	$(function() {
		$("#select").click(function() {
			var page = {
				"phone" : 13225942005,
				"username" : '世界111',
			}
			alert($("#user_id").val());
			$.ajax({
				url : "user/username",
				//url : "users",
				type : "post",
				data : JSON.stringify(page),
				contentType : "application/json;charset=UTF-8",
				//<!-- 向后端传输的数据 -->
				success : function(data) {
					//<!-- 处理后端返回的数据 -->
					if (data == null)
						alert("null");
					var message = JSON.stringify(data);
					$("#select-box").html("查询成功" + message);
				},
				error : function(data) {
					$("#select-box").html("查询失败");
				}
			});
		});
	});
</script>
<body>
	<h1>hello world!</h1>
	<h2>This is my test page!</h2>
	<h3>Welcome to my page!</h3>
	<form class="form-horizontal">
		<label>用户ID</label>
		<div>
			<input type="text" id="user_id" placeholder="请输入用户ID">
		</div>
		<div>
			<button type="button" id="select">查找</button>
		</div>

	</form>
	<div>
		<h2 class="text-danger text-center">
			<!-- 用来显示查找结果 -->
			<span id="select-box"></span>
		</h2>
	</div>
</body>
</html>