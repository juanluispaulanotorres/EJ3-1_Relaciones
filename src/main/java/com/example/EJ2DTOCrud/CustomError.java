package com.example.EJ2DTOCrud;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class CustomError extends Exception{
    private Date timestamp;
    private int HttpCode;
    private String mensaje;

    public CustomError (int HttpCode, String mensaje) {
        super(mensaje);
        Long datetime = System.currentTimeMillis();
        this.timestamp = new Timestamp(datetime);
        this.HttpCode = HttpCode;
        this.mensaje = mensaje;
    }

}
