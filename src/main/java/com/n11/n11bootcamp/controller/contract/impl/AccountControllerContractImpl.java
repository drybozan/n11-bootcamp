package com.n11.n11bootcamp.controller.contract.impl;
import java.util.List;

import com.n11.n11bootcamp.controller.contract.AccountControllerContract;
import com.n11.n11bootcamp.dto.AccountDTO;
import com.n11.n11bootcamp.entity.Account;
import com.n11.n11bootcamp.mapper.AccountMapper;
import com.n11.n11bootcamp.service.AccountEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AccountControllerContractImpl implements AccountControllerContract {

    private final AccountEntityService accountEntityService;

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accountList = accountEntityService.findAll();
        return AccountMapper.INSTANCE.convertToAccountDTOs(accountList);
    }
}
