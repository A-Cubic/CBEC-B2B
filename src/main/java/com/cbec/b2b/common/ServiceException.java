package com.cbec.b2b.common;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
	private String msg;
	
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.msg = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
