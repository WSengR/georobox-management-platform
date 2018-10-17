<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	单个文件上传：</br>
	<form action="/uploadFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/>
		<input type="submit" value="提交上传"/> 
	
	</form>
</body>
</html>