package com.n11.n11bootcamp.dao;

import com.n11.n11bootcamp.entity.Customer;
import com.n11.n11bootcamp.enums.EnumStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 sadece customer nesnesine özel db işlemleri bu interface (dao) katmanında gerçekleşir.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //@Query(" SELECT c FROM Customer c where c.username = :username ")
    Customer findCustomerByUsername(String username);

    List<Customer> findAllByNameAndSurnameAndStatus(String name, String surname, EnumStatus status);

    Customer findByNameAndSurnameAndStatus(String name, String surname, EnumStatus status);

  /*  @Query("select c from Customer c left join Account a on c.id = a.id ")
    Customer findByUsernameWithAccount();*/
}
