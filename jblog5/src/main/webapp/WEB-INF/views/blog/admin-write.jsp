<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> 
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<sec:authentication property="principal" var="authUser"/>
			<ul>
				<c:if test = "${empty authUser or authUser=='anonymousUser'}">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
				
				</c:if>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:if test = "${authUser.id == id }">
				<li><a href="${pageContext.request.contextPath}/${id}/admin/basic">블로그 관리</a></li>
				</c:if>
				</sec:authorize>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${id}/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/category">카테고리</a></li>
					<li class="selected">글작성</li>
				</ul>
				<form action="${pageContext.request.contextPath}/${id}/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select name="categoryNo">
				      				<c:forEach items='${categoryList }' var='categoryvo'>
				      					<option value='${categoryvo.no }'>${categoryvo.name} </option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
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