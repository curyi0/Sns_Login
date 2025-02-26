<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 유저 로그인 후 Admin 접속시 에러( 정상)-->
	<h2>바로가기</h2>
	
	<!-- spring security 적용시 로그인/ 로그아웃에 대한 요청명들
	만약 개발자가 변경하고 싶다면 security 설정( configuration)에서 커스텀하면된다.-->
	<ul>
		<li><a href="/myLogin.do">Custom Login</a></li>
		<li><a href="/guest/index.do">Guest</a> </li>
		<li><a href="/member/index.do">Member</a></li>
		<li><a href="/admin/index.do">Admin</a></li>
		<li><a href="/myLogout.do">logout</a></li>
	</ul>
	<div class="spring" onclick="myAlert();">
	여길 클리개해보세용.</div>
</body>
</html>