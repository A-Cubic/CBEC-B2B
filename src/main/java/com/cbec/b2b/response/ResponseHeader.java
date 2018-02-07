package com.cbec.b2b.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseHeader{
	private String code;
    private String message;
}
