package com.imagic97.ebook.entity;

import org.springframework.util.CollectionUtils;

/**
 * @author imagic
 */
public class ResponseSwap<T> {

    private int status;


    private String message;

    private T data;

    public ResponseSwap(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseSwap(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseSwap(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseSwap(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
