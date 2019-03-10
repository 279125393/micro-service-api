package com.doooly.enums;
/**
 * 定义接口错误码
 * @author hutao
 *
 */
public enum MessageEnum {
	SUCCESS(0, "success"),
	SYS_INTERNAL_ERROR(500,"系统内部错误"),
	FAILED(10000, "失败"),
	PARAMETER_ERROR(10001,"请求参数错误"),
	SIGN_ERROR(10002, "签名验证错误"),
	USER_NOT_EXIST(10100,"用户不存在"),
	USER_TOKEN_NOT_NULL(10104,"token不能为空"),
	USER_TOKEN_NOT_EXIST(10105,"token不存在"),
	;
	
	public Integer msgCode;
	public String msgDesc;
	private MessageEnum(Integer msgCode, String msgDesc) {
		this.msgCode = msgCode;
		this.msgDesc = msgDesc;
	}
	
}
