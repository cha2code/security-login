package org.galapagos.security;

import org.galapagos.domain.MemberVO;
import org.galapagos.mapper.MemberMapper;
import org.galapagos.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper mapper;
	
	// 로그인 시도할 때
	// String username을 가지고 DB에서 찾음
	// 찾으면 userdetails를 리턴 받아서 UserDetails와 입력 받은 password가 맞는 지 확인
	// 없으면 ?error=true 출력
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("Load User By Username : " + username);
		
		MemberVO vo = mapper.read(username);
		
		if(vo == null) {
			throw new UsernameNotFoundException(username + "is not found.");
		}
		
		log.warn("user vo : " + vo);
		
		return new CustomUser(vo);
	}

}
