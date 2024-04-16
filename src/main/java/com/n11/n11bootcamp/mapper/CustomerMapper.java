package com.n11.n11bootcamp.mapper;

import com.n11.n11bootcamp.dto.CustomerDTO;
import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.request.CustomerSaveRequest;
import com.n11.n11bootcamp.request.CustomerUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

//https://mapstruct.org/documentation/stable/reference/html/     daha fazla detay bu linkte

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE) //hedefte mapleme yapamadığı bir field oldugunda ignore et
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    /*
    mapstruct yapısında,karşılıklı maplenecek olan dto ve entity field leri aynı isimde ise otomatik mapleme yapılır ,
    eğer kaynak ile hedef arasındaki field adları farklı ise bunu belirtlememiz lazım bu şekilde :

     @Mapping(target = "name", source = "nameXXX")

     ve mapleme işlemi yapılırken hedefte herhangi bir field ın sabit bir değer almasını istiyorsak bunu şu şekilde
     belirtlememiz lazım :

     @Mapping(target = "status", constant = "PASSIVE")

     */
    @Mapping(target = "status", constant = "PASSIVE")
    @Mapping(target = "name", source = "nameXXX")
    Customer convertToCustomer(CustomerSaveRequest request); //burada arka planda kodu generate yapıyor. source :CustomerSaveRequest,target :Customer
    /*
    kodun generate edilmiş hali bu şekilde :
     @Override
    public Customer convertToCustomer(CustomerSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( request.nameXXX() );
        customer.setSurname( request.surname() );
        customer.setBirthDate( request.birthDate() );
        customer.setUsername( request.username() );
        customer.setIdentityNo( request.identityNo() );
        customer.setPassword( request.password() );
        customer.setPhoneNumber( request.phoneNumber() );
        customer.setEmail( request.email() );

        customer.setStatus( EnumStatus.PASSIVE );

        return customer;
    }*/

    CustomerDTO convertToCustomerDTO(Customer customer);

    List<CustomerDTO> convertToCustomerDTOs(List<Customer> customers);
    /*
     kodun generate edilmiş hali bu şekilde :
        @Override
    public List<CustomerDTO> convertToCustomerDTOs(List<Customer> customers) {
        if ( customers == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( customers.size() );
        for ( Customer customer : customers ) {
            list.add( convertToCustomerDTO( customer ) );
        }

        return list;
    }
     */

    /*
    db ye kaydedilecek nesne hedef olmalı, hedefe elimizdeki datalar set edilir.
     */
    @Mapping(target = "id", ignore = true)
    void updateCustomerFields(@MappingTarget Customer customer, CustomerUpdateRequest request);
}
