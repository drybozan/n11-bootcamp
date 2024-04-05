package com.n11.n11bootcamp.controller.contract.impl;

import com.n11.n11bootcamp.CustomConverter;
import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dao.CustomerRepository;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerControllerContractImpl  implements CustomerControllerContract {

    private final CustomerRepository customerRepository; //yapıcı metot ile enject edilmiş
    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {

        //CustomConverter oluşturularak varlıklar arası veri aktarımı merkezi bir yerde yapılmış oldu. Birden
        //fazla metotta sadece customconverter classı çağırmak yeterli olacaktır.

        Customer customer = CustomConverter.convertToCustomer(request);

        customer = customerRepository.save(customer);

        CustomerDTO customerDTO = CustomConverter.convertToCustomerDTO(customer);

        return  customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<Customer> customerList = customerRepository.findAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList){
            CustomerDTO customerDTO = CustomConverter.convertToCustomerDTO(customer);
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }
}
