package com.nightboot.common.exception;

/**
 * 业务异常
 * 
 * @author nightboot
 */
public final class CommonException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     * <p>
     * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public CommonException()
    {
    }

    public CommonException(String message)
    {
        this.message = message;
    }

    public CommonException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

    public CommonException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public CommonException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }

    public static CommonException fail(String message)
    {
        return new CommonException(message);
    }

    public static CommonException fail(String message,Integer code)
    {
        return new CommonException(message,code);
    }
}