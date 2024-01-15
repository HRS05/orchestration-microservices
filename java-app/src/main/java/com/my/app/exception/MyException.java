package com.my.app.exception;

public class MyException extends Exception{
    public MyException(Exception e) {
        super(e);
        System.out.println(e.getMessage());
    }
}
