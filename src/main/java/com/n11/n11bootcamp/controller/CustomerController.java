package com.n11.n11bootcamp.controller;

import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.general.RestResponse;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdatePasswordRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerControllerContract customerControllerContract;

    public CustomerController(CustomerControllerContract customerControllerContract) {
        this.customerControllerContract = customerControllerContract;
    }



    @PostMapping
    public ResponseEntity<RestResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerSaveRequest saveRequest) {

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

        return ResponseEntity.ok(RestResponse.of(savedCustomer));

    }
    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerControllerContract.getAllCustomers();
        return ResponseEntity.ok(RestResponse.of(allCustomers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerById(@PathVariable  Long id) {
        CustomerDTO customerById = customerControllerContract.getCustomerById(id);
        return ResponseEntity.ok(RestResponse.of(customerById));
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

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerControllerContract.deleteCustomer(id);
    }


    @PatchMapping("/{id}/password")
    public ResponseEntity<RestResponse<CustomerDTO>> updateCustomerPassword(
            @PathVariable  Long id,
           @RequestBody CustomerUpdatePasswordRequest request) {
        CustomerDTO customerDTO = customerControllerContract.updateCustomerPassword(id, request);
        return ResponseEntity.ok(RestResponse.of(customerDTO));
    }

    @GetMapping("/with-username/{username}")
    public ResponseEntity<RestResponse<CustomerDTO>> getCustomerByUsername(@PathVariable @NotBlank String username) {
        CustomerDTO customerById = customerControllerContract.getCustomerByUsername(username);
        return ResponseEntity.ok(RestResponse.of(customerById));
    }
}
