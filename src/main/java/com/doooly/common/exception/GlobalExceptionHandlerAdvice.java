package com.doooly.common.exception;

import com.doooly.dto.MessageRes;
import com.doooly.enums.MessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerAdvice {

   /* @ExceptionHandler(value = BindException.class)
    public MessageRes<String> handler(HttpServletRequest req, BindException ex) {
        FieldError fieldError = ex.getFieldError();
        StringBuilder sb = new StringBuilder();
        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
                .append(fieldError.getDefaultMessage());
        return new MessageRes<String>(400, sb.toString());
    }*/

    @ExceptionHandler(value = Throwable.class)
    public MessageRes<String> handler(HttpServletRequest req, Throwable ex) {
        log.error("exception handler, error="+ex.getMessage(),ex.getCause());
        return new MessageRes<String>(MessageEnum.SYS_INTERNAL_ERROR);
    }


    // 方法参数校验异常
    @ExceptionHandler(value = ConstraintViolationException.class)
    public MessageRes constraintViolationException(Exception e) {
        if (e.getMessage() != null) {
            int index = e.getMessage().indexOf(":");
            return new MessageRes(HttpStatus.BAD_REQUEST.value(), index != -1 ?
                    e.getMessage().substring(index + 1).trim() : e.getMessage());
        }
        return new MessageRes(HttpStatus.BAD_REQUEST.value(), null);
    }


    // Bean 校验异常
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MessageRes notValidExceptionHandler(MethodArgumentNotValidException e) throws Exception {
        MessageRes ret = null;
//        ret.setMsgCode(HttpStatus.BAD_REQUEST.value());
        if (e.getBindingResult() != null && !CollectionUtils.isEmpty(e.getBindingResult().getAllErrors())) {
            ret = new MessageRes(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else {
            ret = new MessageRes(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
        return ret;
    }
}
