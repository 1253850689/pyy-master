package com.zc.pyy.common.exception;


import com.zc.pyy.common.api.CommonResult;
import com.zc.pyy.common.api.IErrorCode;
import com.zc.pyy.common.api.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ZCExceptionHandler {

    @ExceptionHandler(ServerException.class)
    public CommonResult apiExceptionHandler(ServerException e){
        IErrorCode iErrorCode = e.getiErrorCode();
        return CommonResult.failed(iErrorCode);
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }

    @ExceptionHandler(Exception.class)
    public CommonResult apiExceptionHandler(Exception e){
        e.printStackTrace();
        return CommonResult.failed(ResultCode.SERVER_BUSY);
    }
}
