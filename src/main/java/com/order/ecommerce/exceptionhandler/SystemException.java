package com.order.ecommerce.exceptionhandler;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Getter
@Setter
@ToString
@Component
public class SystemException extends RuntimeException {
    private String message;
    private String details;
    private String errorCode;


    public SystemException(String message1, String details, String errorCode) {
        super();
        this.message = message1;
        this.details = details;
        this.errorCode = errorCode;

    }

    public SystemException(String message, String errorCode) {
        super();
        this.message = message;
        this.errorCode = errorCode;
    }
    public SystemException()
    {
    }
}
