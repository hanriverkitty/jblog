<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script>
        function previewImage() {
            const fileInput = document.getElementById('file');
            const logoImage = document.getElementById('logo');

            const file = fileInput.files[0];
            const reader = new FileReader();

            reader.onload = function(e) {
                logoImage.src = e.target.result;
            }

            if (file) {
                reader.readAsDataURL(file);
            }
        }
    </script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<ul>
				<c:if test = "${empty authUser }">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				</c:if>
				<c:if test = "${!empty authUser }">
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				</c:if>
				<c:if test = "${authUser.id == id }">
				<li><a href="${pageContext.request.contextPath}/${id}/admin/basic">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/write">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath}/${id }/admin/basic" method="post" enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value='${blogVo.title }'></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img id='logo' src="${pageContext.request.contextPath}${blogVo.logo}"></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input id='file' type="file" name="file" onchange="previewImage()"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2024
			</p>
		</div>
	</div>
</body>
</html>