package com.n11.n11bootcamp.controller;
import java.util.List;

import com.n11.n11bootcamp.controller.contract.AccountControllerContract;
import com.n11.n11bootcamp.dto.AccountDTO;

import com.n11.n11bootcamp.general.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountControllerContract contract;

    @GetMapping
    public ResponseEntity<RestResponse<List<AccountDTO>>> getAllAccounts() {
        List<AccountDTO> accounts = contract.getAllAccounts();
        return ResponseEntity.ok(RestResponse.of(accounts));
    }
}