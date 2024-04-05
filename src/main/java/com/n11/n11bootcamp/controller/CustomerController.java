package com.n11.n11bootcamp.controller;

import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerControllerContract customerControllerContract;

    public CustomerController(CustomerControllerContract customerControllerContract) {
        this.customerControllerContract = customerControllerContract;
    }


    @PostMapping
    public CustomerDTO saveCustomer(@RequestBody CustomerSaveRequest saveRequest) {

   /*
    {
     "name":"derya",
     "surname":"bozan",
    "birthDate":"1999-04-11",
    "username":"drybozan",
    "identityNo":"11111111111",
     "password":"12345",
     "phoneNumber":"5530014233",
     "email":"deya4545@gmail.com"
}
     */

        CustomerDTO savedCustomer = this.customerControllerContract.saveCustomer(saveRequest);

        return savedCustomer;

    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOList = this.customerControllerContract.getAllCustomers();

        return customerDTOList;

    }
}
