package org.galapagos.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"org.galapagos.controller", "org.galapagos.exception"})
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	// common-upload 라이브러리 사용 시
	@Bean(name="multipartResolver") // bean에 등록할 이름 설정
	public CommonsMultipartResolver getResolver() throws IOException{
		
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		
		resolver.setMaxUploadSize(1024*1024*40); // 40mb
		resolver.setMaxUploadSizePerFile(1024*1024*20); // 20mb
		resolver.setMaxInMemorySize(1024*1024); // 1mb
		
		// fileSystem에 임시로 저장
		resolver.setUploadTempDir(new FileSystemResource("c:\\upload\\tmp"));
		resolver.setDefaultEncoding("UTF-8");
		
		return resolver;
	}

}
