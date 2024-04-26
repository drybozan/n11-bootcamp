package com.n11.n11bootcamp.dto;

import com.n11.n11bootcamp.enums.EnumAccountType;
import com.n11.n11bootcamp.enums.EnumCurrencyType;
import com.n11.n11bootcamp.enums.EnumStatus;

import java.math.BigDecimal;

public record AccountDTO(Long id,
                         String ibanNo,
                         BigDecimal currentBalance,
                         EnumAccountType accountType,
                         EnumCurrencyType currencyType,
                         EnumStatus status) {
}
