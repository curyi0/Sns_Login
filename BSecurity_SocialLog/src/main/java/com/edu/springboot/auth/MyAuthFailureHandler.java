package com.edu.springboot.auth;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//자동으로 생성되는 빈임을 암시
@Configuration
public class MyAuthFailureHandler implements
		AuthenticationFailureHandler{
 //핸들러 제작을 위해 인터페이스를 구현한 후 클래스 정의( 설정파일에서 autowired
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMsg="";
		/*
	security 환경에서 인증시 발생되는 여러가지 케이스를 각각의 조건으로 생성    
	이미 생성된 예외객체를 이용해 적당한 에러메시지 출력   */	

		if( exception instanceof BadCredentialsException) {
//	로그인 실패시 잠금과 같은 기능이 필요하다면 메서드를 별도로 정의후 호출( 필수는아님)
		
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg= "아이디나 비밀번호가 맞지 않아요 다시확인 바랍니다.1";
		}
		else if (exception instanceof InternalAuthenticationServiceException) {
			loginFailureCnt(request.getParameter("my_id"));
			errorMsg= "아이디나 비밀번호가 맞지 않아요 다시확인 바랍니다.2";
		}
		else if(exception instanceof DisabledException) {
			errorMsg="계정이 비활성화 되었어요. 관리자에 문의 하세요.";
		}
		else if(exception instanceof CredentialsExpiredException) {
			errorMsg="비번 유효기간이 만료되었습니다. 관리자에게 문의 하세요4";
		}
		//request영역에 에러메시지 저장 후 로그인페이지로 포워드, error를 파라미터로 전달
		request.setAttribute("errorMsg", errorMsg);
		request.getRequestDispatcher("/myLogin.do?error")
		.forward(request, response);
	}
	public void loginFailureCnt(String username) {
		System.out.println("요청 아이디 "+username);
		//틀린 횟수, 조회 업데이트
	}
}
