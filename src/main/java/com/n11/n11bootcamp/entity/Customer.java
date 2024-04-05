package com.n11.n11bootcamp.entity;


import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.general.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

/*

id,createdAt, createdBy, updatedAt, updatedBy bu alanlar her entity de olmak zorunda
entity nin kim tarafında ne zaman oluşturulduğu veya güncellendiği gib bilgiler tutulmalıdır.
Bütün her yerde olmalıdır tüm entitylerde olması gerekir.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity { // baseentity başka bir entity nin alanlarını bu entity içine gömer , mapleme işlemi yapar.

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer")
    @SequenceGenerator(name = "Customer", sequenceName = "CUSTOMER_ID_SEQ")
    @Id
    private Long id;

    @NotNull
    @Length(min = 1, max = 100)
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @NotNull
    @Length(min = 1, max = 100)
    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Past
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate; // locateDate:05.10.1991 - localtime: 10:15:67 - localDateTime: 05.10.1991 10:15:67

    @NotBlank
    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "IDENTITY_NO", length = 11)
    private String identityNo;

    @NotBlank(message = "PASSWORD CANNOT BE BLANK!!!")
    @Column(name = "PASSWORD", length = 400, nullable = false)
    private String password;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Email
    @NotBlank
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING) // kaydederken enumun ordinal degerini degil string degerini db ye kaydet
    @Column(name = "STATUS", length = 30, nullable = false)
    private EnumStatus status;
}