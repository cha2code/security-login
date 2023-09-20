package org.galapagos.service;

import org.galapagos.domain.MemberVO;

public interface MemberService {
	
	// 한 개 추출
	public MemberVO get(String username);
	
	// 등록
	public void register(MemberVO member);

}
