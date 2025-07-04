package com.n11.n11bootcamp.exceptions;

import com.n11.n11bootcamp.general.BaseErrorMessage;
import com.n11.n11bootcamp.general.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends BusinessException {

    public ItemNotFoundException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage);
    }
}
