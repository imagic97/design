package com.imagic97.ebook.exception;

import com.imagic97.ebook.common.BaseMessageInterface;

/**
 * @author imagic
 */
public class MessageException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMessage;

    public MessageException() {
        super();
    }

    public MessageException(BaseMessageInterface baseMessageInterface) {
        super(baseMessageInterface.getResultCode());
        this.errorCode = baseMessageInterface.getResultCode();
        this.errorMessage = baseMessageInterface.getResultMessage();
    }

    public MessageException(BaseMessageInterface baseMessageInterface, Throwable throwable) {
        super(baseMessageInterface.getResultCode(), throwable);
        this.errorCode = baseMessageInterface.getResultCode();
        this.errorMessage = baseMessageInterface.getResultMessage();
    }

    public MessageException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public MessageException(String errorCode, String errorMessage) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public MessageException(String errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, throwable);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
