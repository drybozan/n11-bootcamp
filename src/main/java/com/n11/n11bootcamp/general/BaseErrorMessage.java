package com.n11.n11bootcamp.general;

import java.io.Serializable;

public interface BaseErrorMessage extends Serializable {

    // enuma mesaj atamak için geliştiriciye zorlamış oluyorum
    String getMessage();
}
