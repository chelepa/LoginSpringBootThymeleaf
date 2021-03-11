package br.com.LoginSpringBootThymeleaf.exceptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.com.LoginSpringBootThymeleaf.constants.ErrorCodes;
import lombok.Getter;

@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
	private String message;
	private List<String> details;

    public ExceptionResponse(final ErrorCodes errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }

}
