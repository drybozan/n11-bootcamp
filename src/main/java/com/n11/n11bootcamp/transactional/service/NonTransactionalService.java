package com.n11.n11bootcamp.transactional.service;

import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.service.CustomerEntityService;
import com.n11.n11bootcamp.transactional.util.TransactionalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;


@Service
@RequiredArgsConstructor
public class NonTransactionalService {

    private final CustomerEntityService customerEntityService;

    private final TransactionalService2 transactionalService2;

    private final NonTransactionalConstantService nonTransactionalConstantService;

    private TransactionalService transactionalService;

    @Autowired
    public void setTransactionalService(@Lazy TransactionalService transactionalService) {
        this.transactionalService = transactionalService;
    }

    public void save() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts1");

        customerEntityService.save(customer);

        System.out.println("end");
    }

    public void saveN2T() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts4");

        customerEntityService.save(customer);

        transactionalService.save();

        System.out.println("end");
    }

    public void saveButError() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts7");

        customerEntityService.save(customer);

        System.out.println("end");

        throw new RuntimeException("error");

        //        try {
        //            testmethod();
        //        } catch (Exception e){
        //            e.printStackTrace();
        //        }

    }

    //    private void testmethod() {
    //        throw new RuntimeException("error");
    //    }

    public void saveN2M() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts11-N");

        customerEntityService.save(customer);

        transactionalService.saveMandatory();

        System.out.println("end");
    }

    public void saveN2S() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts12-T");

        customerEntityService.save(customer);

        transactionalService2.saveSupports();

        System.out.println("end");
    }

    public void doSomething() {

        for (int i = 0; i < 9999; i++) {
            Customer customer = nonTransactionalConstantService.findById(1L);
        }
    }

    public void saveNon2Never() {

        Customer customer = TransactionalUtil.getDummyCustomer("ts20-Non");

        customerEntityService.save(customer);

        nonTransactionalConstantService.saveNever();

        System.out.println("end");
    }
}
