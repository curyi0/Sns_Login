package com.edu.springboot.auth;

import java.util.Map;

import com.edu.springboot.auth.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String provider; // 로그인 제공자 (kakao, naver 등)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
	
    @Builder
    public User(String name,String email, String provider,Role role ) {
    	
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.role=role;
    }
    
    public User update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.name();
    }
}
