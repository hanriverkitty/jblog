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
				<li><a href="">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
					<div class="blog-content">
						<h4>${postList[0].title }</h4>
						<p>
							${postList[0].contents }
						<p>
					</div>
					<ul class="blog-list">
						<c:forEach var="post" items='${postList }' varStatus='status' begin="1">
							<li><a href="">${post.title }</a> <span>${post.regDate}</span>	</li>
						</c:forEach>
					</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}/${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categoryList }" var="categoryVo" varStatus="status">
				<li><a href="${pageContext.request.contextPath}/${id}/${categoryVo.no}">${categoryVo.name}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2024
			</p>
		</div>
	</div>
</body>
</html>