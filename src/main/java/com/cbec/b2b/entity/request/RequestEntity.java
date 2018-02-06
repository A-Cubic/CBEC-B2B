package com.cbec.b2b.entity.request;

import lombok.Data;

@Data
public class RequestEntity<T> {
	private String token;
	private String userId;
	private T params;
}
