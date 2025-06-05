package com.powersales.common;


import lombok.Data;

/**
 * 自定义业务异常
 */
@Data
public class BizException extends RuntimeException {

    private final int code;

    public BizException(String message) {
        super(message);
        this.code = 400;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }
}
