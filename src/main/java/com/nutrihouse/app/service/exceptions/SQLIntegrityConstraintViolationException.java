package com.nutrihouse.app.service.exceptions;

import com.mysql.cj.jdbc.exceptions.SQLError;

import java.sql.SQLException;

public class SQLIntegrityConstraintViolationException extends RuntimeException {

    public SQLIntegrityConstraintViolationException(String msg){
        super(msg);
    }

    public SQLIntegrityConstraintViolationException(String msg, Throwable cause){
        super(msg, cause);
    }
}
