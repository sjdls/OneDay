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
		$("#submit").on(
				"click",
				function() {
					var formData = new FormData();
					formData.append("picture",
							document.getElementById("photo").files[0]);
					formData.append('phone', $("#phone").val());
					formData.append('id', $("#id").val());
					alert($("#phone").val());
					$.ajax({
						type : 'post',
						url : "imageAndJson/uphold",
						data : formData,
						cache : false,
						processData : false,
						contentType : false,
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


	<form>
		图片：<input type="file" name="photo" id="photo"}> 电话：<input
			type="text" name="phone" value="13225942005" id="phone">ID：<input
			type="text" name="id" value="7" id="id"> <input
			type="submit" value="提交" id="submit">
	</form>

	<div>
		<h2 class="text-danger text-center">
			<!-- 用来显示查找结果 -->
			<span id="select-box"></span>
		</h2>
	</div>
</body>
</html>