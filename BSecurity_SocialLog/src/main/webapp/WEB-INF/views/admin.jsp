<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- spring security에서 사용하는 tags-->
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin well</title>
</head>
<body>
	<h2>Admin 영역</h2>
	ADMIN 권한만 접근 가능합니다.
	<!-- ADMIN권한으로 현재페이지에 접근시 ,access에서 지정한데로 로그인 아이디 출력-->
	<s:authorize access="hasRole('ADMIN')">로그인아이디
		<s:authentication property="name"/><!-- 아이디 명 -->
	</s:authorize>
	<%@ include file="/link.jsp" %>
	
</body>
</html>