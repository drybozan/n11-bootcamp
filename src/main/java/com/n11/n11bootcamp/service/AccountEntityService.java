package com.n11.n11bootcamp.service;

import com.n11.n11bootcamp.dao.AccountRepository;
import com.n11.n11bootcamp.entity.Account;
import com.n11.n11bootcamp.general.BaseEntityService;
import org.springframework.stereotype.Service;

/*

Bu class üzerinden db işlemlerini yapan classlara (repository) katmanına erişilir.
Tüm nesneler için base repository olan  BaseEntityService
ve sadece Account nesnesine özel repository işlemleri AccountRepository classlarından extend edilir.
 */
@Service
public class AccountEntityService extends BaseEntityService<Account, AccountRepository> {

    protected AccountEntityService(AccountRepository repository) {
        super(repository);
    }
}
