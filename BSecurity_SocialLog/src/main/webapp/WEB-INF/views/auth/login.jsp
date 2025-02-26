<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="m-5">
	<h2>로그인 폼</h2>
	<div style="width:600px;" class="border border-2 border-success 
		rounded p-5">
		<!--로그인id가 없는 경우 --로그인 폼출력-->
	<c:if test="${empty user_id }" var="loginResult">
	<!-- 파라미터로 전달된 error가 있는 경우 메시지 출력,
	failueru 메서드를 비활성화 해둔 경우만 이 부분을 사용
	로그이 실패시 현재페이지로 error라는 파라미터가  전달-->
	
		<c:if test="${param.error != null}">
		<p>
			Login Error! <br />
			${errorMsg}
		</p>
		</c:if>
		<!-- 시큐리티 설정파일에 지정된 요청명으로 action속성 값 기술-->
		<form action="/myLoginAction.do" method="post">
			<div class="form-floating mb-3 mt-3">
	<!--파일(config) 지정 속성값(getName)-->
				<input type="text" class="form-control" id="user_id" 
					placeholder="Enter email" name="my_id">
				<label for="user_id">아이디</label>
			</div>	
			<div class="form-floating mt-3 mb-3">
				<input type="password" class="form-control" id="user_pwd" 
					placeholder="Enter password" name="my_pass">
				<label for="user_pwd">비밀번호</label>
			</div>	
			<div class="d-grid">
				<button type="submit" class="btn btn-primary btn-block">
					Submit</button>
			</div>
		</form>
	</c:if>
	<c:if test="${not loginResult }">
		 ${user_id } 님, 좋은 아침입니다. <br />
		<a href="/">Root</a>
		<a href="/myLogout.do">Logout</a>	
	</c:if>	
	</div>
</body>
</html>
