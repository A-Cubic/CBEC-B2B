package com.cbec.b2b.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthException extends RuntimeException {
	private int code;
    private String msg;
}
