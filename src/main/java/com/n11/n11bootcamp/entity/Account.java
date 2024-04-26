package com.n11.n11bootcamp.entity;

import com.n11.n11bootcamp.enums.EnumAccountType;
import com.n11.n11bootcamp.enums.EnumCurrencyType;
import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.general.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class Account extends BaseEntity {

    @SequenceGenerator(name = "Account", sequenceName = "ACCOUNT_ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Account")
    @Id
    private Long id;

    @Column(name = "IBAN_NO", length = 30)
    private String ibanNo;

    /*
    * scale, virgulden sonra kaç basamak olacağını belirler
    * precision,toplam hanedeki sayıyı ifade ediyor ,virgul oncesi ve sonrası toplamı
    */

    @Column(name = "CURRENT_BALANCE", precision = 19, scale = 2)
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)// kaydederken enumun ordinal degerini degil string degerini db ye kaydet
    @Column(name = "ACCOUNT_TYPE", length = 30)
    private EnumAccountType accountType;

    @Enumerated(EnumType.STRING)// kaydederken enumun ordinal degerini degil string degerini db ye kaydet
    @Column(name = "CURRENCY_TYPE", length = 30)
    private EnumCurrencyType currencyType;

    @Enumerated(EnumType.STRING) // kaydederken enumun ordinal degerini degil string degerini db ye kaydet
    @Column(name = "STATUS", length = 30)
    private EnumStatus status;
}
