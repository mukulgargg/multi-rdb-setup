package com.multirdbsetup.datatransfer.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author mukulgarg
 * @date 27/05/24
 */

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	String[] messageProperties = {
		"classpath:/messages/en_api_error_messages",
	};
	
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/en_api_error_messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setDefaultLocale(Locale.ENGLISH);
		return messageSource;
	}
}
