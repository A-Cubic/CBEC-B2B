package com.cbec.b2b.response;

import lombok.Data;

@Data
public class Response<T> {
    private T results;
}
