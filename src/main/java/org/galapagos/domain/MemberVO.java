package org.galapagos.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class MemberVO { // user 1명당 만들어야 하는 정보

	@NotBlank(message="ID는 필수 항목입니다.")
	@Size(min = 4, message="4자리 이상 입력하세요.")
	private String username;
	
	@NotBlank(message="Password는 필수 항목입니다.")
	private String password;
	
	@NotBlank(message="Password는 필수 항목입니다.")
	private String password2;
	
	@NotBlank(message="E-mail은 필수 항목입니다.")
	@Email(message="형식에 맞지 않습니다")
	private String email;
	
	private Date regDate;
	private Date updateDate;
	
	// admin의 공통 정보를 제외한 나머지(auth) 저장 list
	private List<AuthVO> authList;
	
	// authList를 Collection<SimpleGrantedAuthority>로 변환
	// security framework는 MemberVO를 모르기 때문 (직접 정의한 것이기 때문에)
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(AuthVO auth : authList) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuth()));
		}
		
		return authorities;
	}
}
