package com.n11.n11bootcamp.controller.contract.impl;

import com.n11.n11bootcamp.controller.contract.CustomerControllerContract;
import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import com.n11.n11bootcamp.errormessage.CustomerErrorMessage;
import com.n11.n11bootcamp.general.BusinessException;
import com.n11.n11bootcamp.mapper.CustomerMapper;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdatePasswordRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import com.n11.n11bootcamp.service.CustomerEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j

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

       customer = customerEntityService.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerEntityService.findByIdWithControl(id);
        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerEntityService.delete(id);
    }

    @Override
    public CustomerDTO updateCustomerPassword(Long id, CustomerUpdatePasswordRequest request) {
        //burada artık customer null mı var mı diye kontrol etmemize gerek yok
        //findByIdWithControl() metodunda kendi yazdığımız item not found exceptionı kontrol ediyor
        // yoksa exception var olamdığını kullanıcıya bildirecektir.
        Customer customer = customerEntityService.findByIdWithControl(id);

        if (!customer.getPassword().equals(request.oldPass())) {
            //kendi business exceptionımızı fırlatıyoruz.
            throw new BusinessException(CustomerErrorMessage.INVALID_OLD_PASSWORD);
        }

        if (!request.newPass().equals(request.newPass2())) {
            throw new BusinessException(CustomerErrorMessage.NEW_PASSWORDS_DID_NOT_MATCH);
        }

        customer.setPassword(request.newPass());
        customer = customerEntityService.save(customer);

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }

    @Override
    public CustomerDTO getCustomerByUsername(String username) {
        Customer customer = customerEntityService.findCustomerByUsername(username);

       List<Customer> customerList =
                customerEntityService.findByNameAndSurnameAndStatus(customer.getName(), customer.getSurname(),
                        EnumStatus.ACTIVE);

        for (Customer customer1 : customerList) {
            log.info(customer1.getUsername());
        }

        return CustomerMapper.INSTANCE.convertToCustomerDTO(customer);
    }
}
