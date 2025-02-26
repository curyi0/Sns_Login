package com.edu.springboot.auth;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;

//임시로 가져오는  유저정보 이후  entity를 만들어서  사용자 정보 저장{JPA}
@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
    private String name;
    private String email;
    private String provider;
    
    @Builder
    public OAuthAttributes(Map<String , Object> attributes, String nameAttributeKey,
    		String name,String email, String provider) {
    	this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.provider = provider;
    }
//	String registrationId = userReq.getClientRegistration().getRegistrationId();
	
    //받아온 정보 attributes 로 변환
	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		if( registrationId.equals("kakao")) {
		}
		return ofKakao(userNameAttributeName, attributes);
//			return ofNaver("id", attributes);
		
	}
        
	
      private static OAuthAttributes ofKakao(String userNameAttributeName,
    		  Map<String, Object> attributes) {
    	  Map<String, Object> kakaoAccount =(Map<String, Object>) attributes.get("kakao_account");
    	  Map<String, Object> profile =(Map<String, Object>) attributes.get("profile");
//    	  Map<String, Object> image =(Map<String, Object>) attributes.get("image");
    	  
    	  return OAuthAttributes.builder()
    			  .name((String) profile.get("nickname"))
    			  .email((String) profile.get("email"))
    			  .provider("kakao")
    			  .attributes(attributes)
    			  .nameAttributeKey(userNameAttributeName)
    			  .build();
      }
      public User toEntity() {
    	    return User.builder()
    	            .name(name)
    	            .email(email)
    	            .provider(provider)
    	            .role(Role.USER)  // 기본 USER 권한 부여
    	            .build();
    	}

//        return new DefaultOAuth2User(
//            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
//            attributes,
//           userNameAttributeName
//           );
}
	

	

