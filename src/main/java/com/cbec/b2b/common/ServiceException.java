package com.cbec.b2b.common;


public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 6506514051998143354L;
	private String msg;
	
	
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

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
