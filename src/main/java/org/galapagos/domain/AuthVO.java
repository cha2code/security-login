package org.galapagos.domain;

import lombok.Data;

@Data
public class AuthVO {

	// MemberVO : AuthVO = 1:1 (Left Join)
	private String username;
	private String auth;
}
