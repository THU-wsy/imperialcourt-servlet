package com.thuwsy.imperialcourt.exception;

/**
 * ClassName: LoginFailedException
 * Package: com.thuwsy.imperialcourt.exception
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/8/21 15:48
 * @Version 1.0
 */
public class LoginFailedException extends RuntimeException{
    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
