package com.edu.springboot.auth;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//user info  받는 service
@Service
@RequiredArgsConstructor
public class OAuthService extends DefaultOAuth2UserService{
	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userReq) {
		OAuth2User oAuth2user= super.loadUser(userReq);
		
		String registrationId = userReq.getClientRegistration().getRegistrationId();
		String userNameAttributeName = userReq.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, 
        		oAuth2user.getAttributes());

        User user = saveOrUpdate(attributes);
        
		return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

	private User saveOrUpdate(OAuthAttributes attributes) {
	    User user = userRepository.findByEmailAndProvider(attributes.getEmail(), attributes.getProvider())
	            .map(entity -> entity.update(attributes.getName()))
	            .orElse(attributes.toEntity());

	    return userRepository.save(user);
	}

}
