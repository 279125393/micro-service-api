package com.doooly.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 外部接口请求参数定义
 * 
 * @author hutao
 *
 * @param <T>
 *            业务请求参数
 */
@Data
public class MessageReq<T> {
	@NotBlank(message="clientId不能为空")
	// 客户端ID，服务端分配
	@ApiModelProperty(value = "客户端ID，服务端分配", required = true)
	private String clientId;
	@NotNull(message ="timestamp不能为空")
	@ApiModelProperty(value = "当前时间戳", required = true)
	private Long timestamp;
	// 签名
	@NotBlank(message = "sign不能为空")
	@ApiModelProperty(value = "业务参数签名", required = true)
	private String sign;
	
	// 业务参数
	@NotNull(message = "param不能为空")
	@Valid
	@ApiModelProperty(value = "业务参数", required = true)
	private T param;
}
