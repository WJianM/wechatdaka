package com.daka.po;

public class Result {
    private Boolean b ;
    private String message;

    public Result() {
    }

    public Result(Boolean b, String message) {
        this.b = b;
        this.message = message;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
