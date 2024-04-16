package com.n11.n11bootcamp.controller.contract.impl;

import com.n11.n11bootcamp.converter.CustomConverter;
import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dao.CustomerRepository;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.mapper.CustomerMapper;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import com.n11.n11bootcamp.service.entityservice.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerControllerContractImpl  implements CustomerControllerContract {

   // private final CustomerRepository customerRepository; //yapıcı metot ile enject edilmiş, burası artık entityservice içine çekilde generic hale getirilerek
   private final CustomerEntityService customerEntityService;

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {

        //CustomConverter oluşturularak varlıklar arası veri aktarımı merkezi bir yerde yapılmış oldu. Birden
        //fazla metotta sadece customconverter classı çağırmak yeterli olacaktır.

       // Customer customer = CustomConverter.convertToCustomer(request); converter yerine artık mapper yapısı kullanıldı.

        Customer customer = CustomerMapper.INSTANCE.convertToCustomer(request);

       // customer = customerRepository.save(customer);

        customer = customerEntityService.save(customer);

       // CustomerDTO customerDTO = CustomConverter.convertToCustomerDTO(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

      //  List<Customer> customerList = customerRepository.findAll();

        List<Customer> customerList = customerEntityService.findAll();

    /*    List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList){
            CustomerDTO customerDTO = CustomConverter.convertToCustomerDTO(customer);
            customerDTOList.add(customerDTO);
        }
*/
        List<CustomerDTO> customerDTOList = CustomerMapper.INSTANCE.convertToCustomerDTOs(customerList);
        return customerDTOList;
    }

    @Override
    public CustomerDTO updateCustomer(Long debugCustomerId, CustomerUpdateRequest request) {

        Customer customer = customerEntityService.findByIdWithControl(debugCustomerId);
        CustomerMapper.INSTANCE.updateCustomerFields(customer, request);

        customerEntityService.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }
}
