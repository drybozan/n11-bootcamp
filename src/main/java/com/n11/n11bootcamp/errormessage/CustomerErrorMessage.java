package com.n11.n11bootcamp.errormessage;

import com.n11.n11bootcamp.general.BaseErrorMessage;

/*
* customer nesneisine özel eror mesjları bu enumda bulunur. Genel mesajlar ise general klasörü altındaki GeneralErrorMessage altında bulunur.*/

public enum CustomerErrorMessage implements BaseErrorMessage {

    INVALID_OLD_PASSWORD("Invalid old password!"),
    NEW_PASSWORDS_DID_NOT_MATCH("New passwords did not match");
    ;

    private final String message;

    CustomerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
