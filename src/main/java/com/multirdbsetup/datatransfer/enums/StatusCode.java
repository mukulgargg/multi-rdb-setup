package com.multirdbsetup.datatransfer.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
	
	SUCCESS("SUCCESS"), FAILURE("FAILURE");
	
	private final String value;
	
	StatusCode(String value) {
		this.value = value;
	}
	
	public static StatusCode get(String code) {
		if (code.isEmpty()) {
			return null;
		}
		for (StatusCode statusCode : StatusCode.values()) {
			if (statusCode.getValue().equalsIgnoreCase(code.trim())) {
				return statusCode;
			}
		}
		throw new IllegalArgumentException("No matching type for code: " + code);
	}
}