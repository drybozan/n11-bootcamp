package com.n11.n11bootcamp.general;

import jakarta.persistence.*;
import lombok.*;

/**
 * bir abstract class, extend edildiği classa mapleme işlemi yapar.
 * Yani bu classı extedns eden bir entity kendi içine BaseAdditionalFields
 * classını da gömmüş olur.
 */
@MappedSuperclass //extend edildiği classa mapleme işlemi yapar.
@Getter
@Setter
public abstract class BaseEntity {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;
}