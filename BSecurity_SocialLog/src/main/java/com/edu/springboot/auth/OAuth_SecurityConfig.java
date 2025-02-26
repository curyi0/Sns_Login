package com.edu.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
/*
 스프링 컨테이너가 시작될때  빈이 생성되어야 하기에 
 기본패키지 하위에 정의 후 @을 부착한다. 스프링 security 사용을 위한 설정 클래스로 정의 했다.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth_SecurityConfig { 

//	인증실패에 대한 핸들러를 제작했다면 사용을 위해 빈 자동주입 받음
//	failerHandler 메서드 추가
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;
	
	@Autowired
	private final OAuthService OauthService;
	
	@Bean
	public SecurityFilterChain fiterChain(HttpSecurity http	) 
		throws Exception{
			http.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)

				.authorizeHttpRequests((request)->request
			//dispatcher( jakarta.servlet import, Security6.1vr 부터 직접적사용x?)
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/css/**","/js/**","/images/**").permitAll()
				.requestMatchers("/guest/**").permitAll()
				.anyRequest().authenticated()
//					.requestMatchers("/member/**").hasAnyRole("USER","ADMIN")
//					.requestMatchers("/admin/**").hasRole("ADMIN")
				
			)
			
				//로그인 성공 실패
			.oauth2Login(oauth2-> oauth2
					.defaultSuccessUrl("/loginSuccess")
					.userInfoEndpoint(userInfo-> userInfo
							.userService(OauthService)
					)
							);
				return http.build();
	}
			/*
	로그인폼 커스텀	(디폴트가 아닌 지정한 요청명을 통해 전송),
	loginPage   로그인 페이지 요청명
	loginProcessingUrl  : 폼값 전송하여 로그인을 처리할 요청명
	failureUrl  : 로그인 실패시 이동할 요청명
	failureHadler  :별도 핸들러 빈을 등록 후 에러처리
	
	usernameParameter  아이디 입력위한 <input>의 name속성 값
	passwordParameter    패스워드 입력위한 name속성 값
	*/
	//패스워드 인코더 비번 저장전에 암호화 하기
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}


//		@Bean
//		public UserDetailsService users() {
//			
//			UserDetails user= User.builder()
//					.username("user")
//					.password(passwordEncoder().encode("1234"))
//					.roles("USER")
//					.build();
//			
//			UserDetails admin =User.builder()
//					.username("admin")
//					.password(passwordEncoder().encode("1234"))
//					.roles("USER","ADMIN")
//					.build();
//			
//			return new InMemoryUserDetailsManager(user, admin);
//		}
//	}

