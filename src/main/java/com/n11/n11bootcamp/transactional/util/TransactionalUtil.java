package com.n11.n11bootcamp.transactional.util;

import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

/*
data sağlamak amaç
 */
public class TransactionalUtil {

    public static Customer getDummyCustomer(String suffix) {

        String testName = "test";
        if (StringUtils.hasText(suffix)) {
            testName = testName + "-" + suffix;
        }

        Customer customer = new Customer();
        customer.setName(testName);
        customer.setSurname(testName);
        customer.setIdentityNo("12312312312");
        customer.setPassword("123");
        customer.setBirthDate(LocalDate.now());
        customer.setUsername("sbahadirm");
        customer.setPhoneNumber("3367434");
        customer.setEmail("sbahasdgsjkhd@gfmail.com");
        customer.setStatus(EnumStatus.ACTIVE);

        return customer;
    }
}