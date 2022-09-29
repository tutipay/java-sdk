package com.tuti.api.data;

import okhttp3.Headers;

public class ResponseData {
    private int code;
    private String body;
    private Headers headers;

    public ResponseData(int code, String body, Headers headers) {
        this.code = code;
        this.body = body;
        this.headers = headers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }
}
