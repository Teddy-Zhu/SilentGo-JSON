package com.silentgo.json.exception;

/**
 * Project : json
 * Package : com.silentgo.json.exception
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/9.
 */
public class DeserializerException extends RuntimeException {

    public DeserializerException(String message) {
        super(message);
    }

    public DeserializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
