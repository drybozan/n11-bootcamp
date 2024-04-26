package com.n11.n11bootcamp.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerUpdatePasswordRequest(@NotBlank String oldPass,
                                            @NotBlank String newPass,
                                            @NotBlank String newPass2) {

}
