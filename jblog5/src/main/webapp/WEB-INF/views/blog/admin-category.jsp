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
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(categoryList) }"/>
		      		<c:forEach items='${categoryList }' var='categoryVo' varStatus='status'>
			      		<tr>
							<td>${count-status.index }</td>
							<td>${categoryVo.name }</td>
							<td>${countPostList[status.index]}</td>
							<td>${categoryVo.description }</td>
							<td>
								<a href="${pageContext.request.contextPath}/${id }/admin/category/delete?categoryNo=${categoryVo.no}">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
								</a>
							</td>
						</tr>
		      		</c:forEach>					  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.request.contextPath}/${id}/admin/category" method="post">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
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