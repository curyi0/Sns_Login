package com.edu.springboot;

import java.security.Principal;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {
		return "home";
		
	}
	@RequestMapping("/kakao_login")
	public String kakao_login(HttpServletRequest req) {
		String client_id = "002530790b11a25d6fc0044f6a9b5fe9";
		String redirect_uri = "http://localhost:8586";
		String state = UUID.randomUUID().toString();
		String login_url = "https://kauth.kakao.com/oauth/authorize?response_type=code"
							+ "&client_id=" + client_id
							+ "&redirect_uri=" + redirect_uri
							+ "&state=" + state;
		return "redirect:";
	}
	

	
}
