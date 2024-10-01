package com.multirdbsetup.datatransfer.enums;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;

@Getter
public enum ResultCode {
	
	ETI058,
	SRC001,
	EUA012;
	
	public static ResultCode get(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		return ResultCode.valueOf(code);
	}
}