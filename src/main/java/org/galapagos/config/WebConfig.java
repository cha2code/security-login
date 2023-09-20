package org.galapagos.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	// default로 만든 xml 설정과 같음
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// 한글 등록을 위한 필터 설정
	// 한글로 작성된 데이터가 깨지지 않게 설정
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		// handler가 발생하지 않으면 예외로 처리 (contoller를 못 찾으면-mapping된 것이 없으면)
		// 여기서는 404error를 예외로 처리함
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
	}

}
