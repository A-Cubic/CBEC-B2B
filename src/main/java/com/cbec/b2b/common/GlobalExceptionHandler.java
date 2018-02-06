package com.cbec.b2b.common;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbec.b2b.response.Response;

/**  
 * 异常拦截处理器  
 */  
@ControllerAdvice  
@ResponseBody
public class GlobalExceptionHandler {
	
	private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
    //运行时异常    
    @ExceptionHandler(RuntimeException.class)  
    public Response runtimeExceptionHandler(RuntimeException ex) {  
        return resultFormat(1, ex);  
    }  
  
    //空指针异常    
    @ExceptionHandler(NullPointerException.class)  
    public Response nullPointerExceptionHandler(NullPointerException ex) {  
        return resultFormat(2, ex);  
    }  
  
    //类型转换异常    
    @ExceptionHandler(ClassCastException.class)  
    public Response classCastExceptionHandler(ClassCastException ex) {  
        return resultFormat(3, ex);  
    }  
  
    //IO异常    
    @ExceptionHandler(IOException.class)  
    public Response iOExceptionHandler(IOException ex) {  
        return resultFormat(4, ex);  
    }  
  
    //未知方法异常    
    @ExceptionHandler(NoSuchMethodException.class)  
    public Response noSuchMethodExceptionHandler(NoSuchMethodException ex) {  
        return resultFormat(5, ex);  
    }  
  
    //数组越界异常    
    @ExceptionHandler(IndexOutOfBoundsException.class)  
    public Response indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {  
        return resultFormat(6, ex);  
    }  
  
    //400错误    
    @ExceptionHandler({HttpMessageNotReadableException.class})  
    public Response requestNotReadable(HttpMessageNotReadableException ex) {  
        System.out.println("400..requestNotReadable");  
        return resultFormat(7, ex);  
    }  
  
    //400错误    
    @ExceptionHandler({TypeMismatchException.class})  
    public Response requestTypeMismatch(TypeMismatchException ex) {  
        System.out.println("400..TypeMismatchException");  
        return resultFormat(8, ex);  
    }  
  
    //400错误    
    @ExceptionHandler({MissingServletRequestParameterException.class})  
    public Response requestMissingServletRequest(MissingServletRequestParameterException ex) {  
        System.out.println("400..MissingServletRequest");  
        return resultFormat(9, ex);  
    }  
  
    //405错误    
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})  
    public Response request405(HttpRequestMethodNotSupportedException ex) {  
        return resultFormat(10, ex);  
    }  
  
    //406错误    
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})  
    public Response request406(HttpMediaTypeNotAcceptableException ex) {  
        System.out.println("406...");  
        return resultFormat(11, ex);  
    }  
  
    //500错误    
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})  
    public Response server500(RuntimeException ex) {  
        System.out.println("500...");  
        return resultFormat(12, ex);  
    }  
  
    //栈溢出  
    @ExceptionHandler({StackOverflowError.class})  
    public Response requestStackOverflow(StackOverflowError ex) {  
        return resultFormat(13, ex);  
    }  
  
    //其他错误  
	@ExceptionHandler({Exception.class})  
    public Response exception(Exception ex) {  
        return resultFormat(14, ex);  
    }  
	
	//token错误  
	@ExceptionHandler({TokenException.class})  
    public Response exception(TokenException ex) {  
        return resultFormat(ex);  
    }  
	
	//权限错误  
	@ExceptionHandler({AuthException.class})  
    public Response exception(AuthException ex) {  
        return authFormat(ex);  
    }  
  
    private <T extends Throwable> Response resultFormat(Integer code, T ex) {  
        logger.error(String.format(logExceptionFormat, code, ex.getMessage()));  
        Response respones = new Response();
        respones.failed(code, ex.getMessage());
        return respones;  
    }  
    
    private <T extends Throwable> Response resultFormat(TokenException ex) {  
        logger.error(String.format(logExceptionFormat, ex.getCode(), ex.getMsg()));  
        Response response = new Response();
        response.failed(ex.getCode(), ex.getMsg());
        return response;  
    }  
    
    private <T extends Throwable> Response authFormat(AuthException ex) {  
        logger.error(String.format(logExceptionFormat, ex.getCode(), ex.getMsg()));  
        Response response = new Response();
        response.failed(ex.getCode(), ex.getMsg());
        return response;
    }  
}
