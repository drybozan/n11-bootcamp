package com.n11.n11bootcamp.converter;

import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.request.CustomerSaveRequest;

/*
mapperstruct yapısı kurduğumuz için artık bu classı kullanmaya gerek kalmıyor.
 */
public class CustomConverter {

    public static CustomerDTO convertToCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getSurname(), customer.getBirthDate()
                ,customer.getUsername(), customer.getIdentityNo(), customer.getPhoneNumber(), customer.getEmail(),customer.getStatus());

        return customerDTO;
    }

    public static Customer convertToCustomer(CustomerSaveRequest saveRequest){

        Customer customer = new Customer();
        customer.setName(saveRequest.nameXXX()); //record uzerinden get islemi
        customer.setSurname(saveRequest.surname());
        customer.setEmail(saveRequest.email());
        customer.setBirthDate(saveRequest.birthDate());
        customer.setIdentityNo(saveRequest.identityNo());
        customer.setPassword(saveRequest.password());
        customer.setUsername(saveRequest.username());
        customer.setPhoneNumber(saveRequest.phoneNumber());
        customer.setStatus(EnumStatus.ACTIVE);

        return customer;
    }
}
