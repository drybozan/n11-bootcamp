package com.n11.n11bootcamp.transactional.service;

import com.n11.n11bootcamp.dao.CustomerRepository;
import com.n11.n11bootcamp.entity.Customer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionalConstantService {

    private final CustomerRepository customerDao;

    private Map<Long, Customer> map = new LinkedHashMap<>();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Customer findById(Long id){

        Customer customer = map.get(id);
        if (customer != null){
            return customer;
        }

        Optional<Customer> customerOptional = customerDao.findById(id);

        if (customerOptional.isPresent()){
            customer = customerOptional.get();
        } else {
            throw new RuntimeException("Error");
        }

        map.put(customer.getId(), customer);

        return customer;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Customer findByIdWithNewTransaction(Long id){

        Customer customer = map.get(id);
        if (customer != null){
            return customer;
        }

        Optional<Customer> customerOptional = customerDao.findById(id);

        if (customerOptional.isPresent()){
            customer = customerOptional.get();
        } else {
            throw new RuntimeException("Error");
        }

        map.put(customer.getId(), customer);

        return customer;
    }
}