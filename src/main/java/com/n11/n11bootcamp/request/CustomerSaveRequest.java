package com.n11.n11bootcamp.request;

import java.time.LocalDate;

/*
 * javada record sayesinde getter setter yazmaya gerek kalmıyor.
 * immutable bir tiptir.Bu yüzden setter yazmaya da izin vermiyor.Üzerinde değerleri . (nokta) ile alabiliyoruz.
 * Request ve response lar için ideal bir tiptir.*
 * */
public record CustomerSaveRequest(String name,
                                  String surname,
                                  LocalDate birthDate,
                                  String username,
                                  String identityNo,
                                  String password,
                                  String phoneNumber,
                                  String email) {

}
