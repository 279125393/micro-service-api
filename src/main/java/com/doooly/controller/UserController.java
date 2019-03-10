package com.doooly.controller;


import com.alibaba.fastjson.JSONObject;
import com.doooly.common.BaseController;
import com.doooly.dto.MessageReq;
import com.doooly.dto.MessageRes;
import com.doooly.enums.MessageEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hutao
 * @since 2019-03-10
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "用户api")
public class UserController extends BaseController {

    @ApiOperation("get test")
    @GetMapping("/{name}")
    public String sayHello(@PathVariable("name")String name){
        log.info("name is {}",name);
        return "hello, " + name;
    }

    @ApiOperation("post test")
    @PostMapping("/test")
    public MessageRes<String> test(@Validated @RequestBody MessageReq<String> req){
        log.info(JSONObject.toJSONString(req.getParam()));
        return new MessageRes<String>(MessageEnum.SUCCESS);
    }
}
