package com.edu.springboot.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

//	ROLE이 항상 앞에 붙어서 사용
	GUEST("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "사용자");
	
	private final String key;
	private final String title;
	
}
