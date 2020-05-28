<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>image/uphold</title>
</head>
<body>
    <form action="user/profilePhoto" method="post" enctype="multipart/form-data">
        图片：<input type="file" name="photo">
        电话：<input type="text" name="phone" value="13225942005">
        <input type="submit" value="提交">
    </form>
</body>
</html>