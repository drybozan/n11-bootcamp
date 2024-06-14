package com.n11.n11bootcamp.controller.contract;

import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdatePasswordRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerControllerContract {
    CustomerDTO saveCustomer(CustomerSaveRequest request);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer( Long debugCustomerId,
                              CustomerUpdateRequest request);

    CustomerDTO getCustomerById(Long id);

    void deleteCustomer(Long id);

    CustomerDTO updateCustomerPassword(Long id, CustomerUpdatePasswordRequest request);

    CustomerDTO getCustomerByUsername(String username);
}
