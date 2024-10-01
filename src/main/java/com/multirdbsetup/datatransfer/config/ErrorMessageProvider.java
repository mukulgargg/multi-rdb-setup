package com.multirdbsetup.datatransfer.config;

import com.multirdbsetup.datatransfer.enums.ResultCode;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageProvider {
	
	@Autowired
	private MessageSource messageSource;
	
	public String getMessage(ResultCode resultCode) {
		String errorMessage = messageSource.getMessage(resultCode.name(), null, Locale.ENGLISH);
		return errorMessage.isEmpty() ? "INTERNAL SERVER ERROR" : errorMessage;
	}
	
	public String getMessage(ResultCode resultCode, Object[] objectArray) {
		String errorMessage = messageSource.getMessage(resultCode.name(), objectArray, Locale.ENGLISH);
		return errorMessage.isEmpty() ? "INTERNAL SERVER ERROR" : errorMessage;
	}
}