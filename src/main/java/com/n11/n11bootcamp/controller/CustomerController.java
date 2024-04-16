package com.n11.n11bootcamp.controller;

import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.general.RestResponse;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RestResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerControllerContract.getAllCustomers();
        return ResponseEntity.ok(RestResponse.of(allCustomers));
    }

    ///**
    // * PUT -> api/customers/6478545
    // * @param debugCustomerId
    // * @param customer
    // * @return
    // */
    @PutMapping("/{debugCustomerId}")
   public ResponseEntity<RestResponse<CustomerDTO>> updateCustomer (@PathVariable  Long debugCustomerId, @RequestBody CustomerUpdateRequest request) {
        CustomerDTO customerDTO = customerControllerContract.updateCustomer(debugCustomerId,request);
        return ResponseEntity.ok(RestResponse.of(customerDTO));

    }
}
