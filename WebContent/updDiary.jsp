<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

<body>
	<form  action="diary/updDiary" method="post" enctype="multipart/form-data">
		图片：<input type="file" name="picture" id="picture"}> 
		id：<input type="text" name="id" value="22" id="id">
		电话：<input type="text" name="phone" value="13225942005" id="phone">
		title：<input type="text" name="title" value="标题" id="title"> 
		weather：<input type="text" name="weather" value="天气" id="weather"> 
		mood：<input type="text" name="mood" value="心情" id="mood"> 
		event：<input type="text" name="event" value="事件" id="event"> 
		content：<input type="text" name="content" value="内容" id="content"> 
		draft：<input type="text" name="draft" value="false" id="draft">
		<input type="submit" value="提交" id="submit">
	</form>

	<div>
		<h2 class="text-danger text-center">
			<!-- 用来显示查找结果 -->
			<span id="select-box"></span>
		</h2>
	</div>
</body>
</html>