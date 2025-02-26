<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>스프링 부트 프로젝트</h2>
	<ul>
		<li><a href="/">root</a></li>
	</ul>
	
	<script>window.onload= function(){
		myConsole("씨큐리티(보안)");
	}</script>
	<h1>Social Log</h1>
	
	<ul>
		<li><a href="/oauth2/authorization/kakao">카카오로 로그인</a></li>
		<li><a href="/oauth2/authorization/naver">네이버로 로그인</a></li>
	</ul>
	
	<div class="spring" onclick="myAlert();">
	여길 클리개해보세용.</div>
</body>
</html>