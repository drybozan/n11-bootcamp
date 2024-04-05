package com.n11.n11bootcamp.general;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * createdAt, createdBy, updatedAt, updatedBy
 * @author bahadirmemis
 */
@Embeddable // gömülebilir , bir entity nin içine gömülür hale getirilir.
@Getter
@Setter
public class BaseAdditionalFields {

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long creatorId;
    private Long updaterId;
}
