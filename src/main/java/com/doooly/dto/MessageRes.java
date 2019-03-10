package com.doooly.dto;

import com.doooly.enums.MessageEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 外部接口响应结果定义
 * 
 * @author hutao
 *
 * @param <T>
 *            成功时，返回业务数据
 */
@Data
public class MessageRes<T> {
	@ApiModelProperty("返回码")
	private Integer msgCode;
	@ApiModelProperty("返回码描述")
	private String msgDesc;


	@ApiModelProperty("业务数据")
	private T data;

	public MessageRes(Integer msgCode, String msgDesc) {
		this.msgCode = msgCode;
		this.msgDesc = msgDesc;
	}

	public MessageRes(MessageEnum message) {
		super();
		this.msgCode = message.msgCode;
		this.msgDesc = message.msgDesc;
	}

	public MessageRes(MessageEnum message, T data) {
		super();
		this.msgCode = message.msgCode;
		this.msgDesc = message.msgDesc;
		this.data = data;
	}
}
