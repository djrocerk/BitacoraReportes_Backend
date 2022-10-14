package com.reportesclaro.Security.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorMessage extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String resourceName;

    public ErrorMessage(String resourceName){
        super(String.format("mensaje: %s", resourceName ));
        this.resourceName = resourceName;
    }

}
