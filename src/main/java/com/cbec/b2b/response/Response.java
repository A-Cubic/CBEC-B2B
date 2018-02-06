package com.cbec.b2b.response;

import java.io.Serializable;

import com.google.common.base.Objects;

public class Response<T> implements Serializable{
	
	private static final long serialVersionUID = 4625159953302970302L;
	private boolean success = true;
    private T results;
    private String error="";
	
    public Response() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getResults() {
        return this.results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.success = false;
        this.error = error;
    }
    
    public void failed(int code, String msg) { 
    	 this.success = false;
         this.error = "错误代码:"+code+"原因："+msg;
    }  

    public String toString() {
        return Objects.toStringHelper(this).add("success", this.success).add("results", this.results).add("error", this.error).omitNullValues().toString();
    }
}
