package com.doooly.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类 在与spring boot集成时，放在与Application.java同级的目录下。
 * 通过@Configuration注解，让Spring来加载该类配置。 再通过@EnableSwagger2注解来启用Swagger2。
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

	/**
	 * 创建API应用 apiInfo() 增加API相关信息
	 * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
	 * 本例采用指定扫描的包路径来定义指定要建立API的目录。
	 * 
	 * @return
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.doooly.controller")).paths(PathSelectors.any())
				.build();
	}

	/**
	 * 创建该API的基本信息（这些基本信息会展现在文档页面中） 访问地址：http://项目实际地址/swagger-ui.html
	 * 
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("xx联盟平台Rest API")
				.description("xx联盟平台开发者平台...")
				.contact("279125393@qq.com")
				.description("为保证开放REST API数据传输安全，使用API时请遵守以下约定：\n "
						+ "1.设置 Header头：token(登录xx平台后服务端返回用户令牌)，userId(才子用户ID)。备注:用户登录API和查询微信小程序配置API 除外。\n"
						+ "2.所有REST API业务接口都需要SIGN签名，接口请求参数按字典序排序以k1=v1&k2=v2&k3=v3&... 其中业务参数也按字典序排序后同样规则拼接，将拼接的字符串+clientSecret(由服务端提供)进行SHA256加密后为sign。")
				.version("1.0").build();
	}
}