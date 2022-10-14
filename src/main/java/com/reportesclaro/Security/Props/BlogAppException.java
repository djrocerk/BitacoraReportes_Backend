package com.reportesclaro.Security.Props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BlogAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus estado;

    private String mensaje;

    public BlogAppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public BlogAppException(HttpStatus estado, String mensaje, String mensaje1) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }
}
