package org.galapagos.controller;

import javax.validation.Valid;

import org.galapagos.domain.MemberVO;
import org.galapagos.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/security")
@Controller
public class SecurityController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/login")
	public void login() {
		
		log.info("login page");
	}
	
	@GetMapping("/signup")
	public void signUp(@ModelAttribute("member") MemberVO member) {
		
	}
	
	@PostMapping("/signup")
	public String signUp(@Valid @ModelAttribute("member") MemberVO member,
			Errors errors) {
		
		// 비밀번호, 비밀번호 확인 일치 여부
		if(!member.getPassword().equals(member.getPassword2())) {
			
			// 특정 필드에 대해 에러 추가
			// (변수명, 에러코드, 출력할 메세지)
			errors.rejectValue("password2", "비밀번호확인 에러", "비밀번호 확인이 일치하지 않습니다.");
		}
		
		// 아이디 중복 체크 (유효성 검사 통과 시)
		if(!errors.hasFieldErrors("username")) {
			if(service.get(member.getUsername()) != null) { // 중복일 경우
				
				errors.rejectValue("username", "아이디 중복 에러", "동일한 ID가 있습니다.");
			}
		}
		
		if(errors.hasFieldErrors()) {
			
			return "security/signup";
		}
		
		// DB 저장
		
		return "redirect:/";
		
	}

}
