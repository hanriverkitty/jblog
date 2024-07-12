<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> 
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

<script>

$(function() {
	$("#btn-check").click(function() {
		var id = $("#id").val();
		console.log(id);
		if(id==''){
			return;
		}
		$.ajax({
			url:"/jblog5/user/api/checkid?id="+id,
			type:"get",
			dataType:"json",
			error:function(xhr,status,error){
				console.error(err);
			},
			success:function(response){
				if(response.result=="fail"){
					console.error(response.message);
					return;
				}
				if(response.data){
					alert("존재하는 아이디입니다. 다른 아이디를 사용해 주세요.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				// 사용가능한 이메일
				$("#btn-check").hide()
				$("#img-check").show()
			}
		});
	})
});
</script>

</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<sec:authentication property="principal" var="authUser"/>
			<ul class="menu">
				<c:if test = "${empty authUser or authUser=='anonymousUser'}">
					<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
				</c:if>
				<sec:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
				</sec:authorize>
			</ul>
		<form:form
			modelAttribute="userVo"
		    class="join-form" 
		    id="join-form" 
		    method="post"
		    action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id"/> 
			<input id="btn-check" type="button" value="id 중복체크">
			<img id="img-check" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:password path="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
