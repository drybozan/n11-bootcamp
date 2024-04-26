package com.n11.n11bootcamp.service;

import com.n11.n11bootcamp.dao.CustomerRepository;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.general.BaseEntityService;
import org.springframework.stereotype.Service;
import com.n11.n11bootcamp.enums.EnumStatus;

import java.util.List;

/*

Bu class üzerinde db işlemlerini yapan classlara (repository) katmanına erişilir.
Tüm nesneler için base repository olan  BaseEntityService ve sadece customer nesnesine özel
repository işlemleri CustomerRepository classlarından extend edilir.
 */
@Service
public class CustomerEntityService extends BaseEntityService<Customer, CustomerRepository> {

    protected CustomerEntityService(CustomerRepository repository) {
        super(repository);
    }

    public Customer findCustomerByUsername(String username) {
        return getRepository().findCustomerByUsername(username);
    }

    public List<Customer> findByNameAndSurnameAndStatus(String name, String surname, EnumStatus status) {
        return getRepository().findAllByNameAndSurnameAndStatus(name, surname, status);
    }


}
