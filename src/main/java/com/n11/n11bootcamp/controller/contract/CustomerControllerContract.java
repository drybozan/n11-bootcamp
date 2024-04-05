package com.n11.n11bootcamp.controller.contract;

import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.request.CustomerSaveRequest;

import java.util.List;

public interface CustomerControllerContract {
    CustomerDTO saveCustomer(CustomerSaveRequest request);

    List<CustomerDTO> getAllCustomers();
}
