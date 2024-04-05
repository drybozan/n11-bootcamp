package com.n11.n11bootcamp;

import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.request.CustomerSaveRequest;

public class CustomConverter {

    public static CustomerDTO convertToCustomerDTO(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getSurname(), customer.getBirthDate()
                ,customer.getUsername(), customer.getIdentityNo(), customer.getPhoneNumber(), customer.getEmail(),customer.getStatus());

        return customerDTO;
    }

    public static Customer convertToCustomer(CustomerSaveRequest saveRequest){

        Customer customer = new Customer();
        customer.setName(saveRequest.name()); //record uzerinden get islemi
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
