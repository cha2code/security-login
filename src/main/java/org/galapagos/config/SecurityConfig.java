package org.galapagos.config;

import javax.sql.DataSource;

import org.galapagos.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// RootConfig의 DataSource
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // 요청에 대해 접근 권한 설정

				// 모두 허용
				.antMatchers("/security/all").permitAll()

				// 특정 역할에게만 허용
				.antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/security/member").access("hasRole('ROLE_MEMBER')")

				// 로그인 사용자에게만 허용
				.antMatchers("/board/write", "board/modify", "board/delete")
				.authenticated();

		http.formLogin() // login form에 대한 설정
		.loginPage("/security/login") // get
		.loginProcessingUrl("/security/login") // post
		.defaultSuccessUrl("/")
		.failureUrl("/security/login?error=true"); // el:param.error로 접근
		
		http.logout()
		.logoutUrl("/security/logout") // post방식(로그아웃 호출 url) = csrf 토큰 필요
		.invalidateHttpSession(true)
		.deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
		.logoutSuccessUrl("/security/login"); // 로그아웃 후 이동할 페이지
		
		http.rememberMe() // remember-me 기능 설정
		.key("galapagos")
		.tokenRepository(persistentTokenRepository())
		.tokenValiditySeconds(7*24*60*60); // 7일 동안 로그인 유지
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication() // 메모리에 user 정보 설정
		.withUser("admin")
		.password("$2a$10$O/IR5CPd.w.yiSflV6U5TOPKqaEg4eT49qQ7Y96D9Rg/UoKXvA3hC") // 암호화 되지 않음 (no operation)
		.roles("ADMIN"); // 앞에 'ROLE_'을 붙이지 않아도 알아서 삽입함
		
		auth.inMemoryAuthentication()
		.withUser("member")
		//.password("{noop}1234")
		.password("$2a$10$O/IR5CPd.w.yiSflV6U5TOPKqaEg4eT49qQ7Y96D9Rg/UoKXvA3hC")
		.roles("MEMBER");
		
		auth
		.userDetailsService(customUserService())
		.passwordEncoder(passwordEncoder());
	}
	
	// password 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService customUserService() {
		
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}

}
