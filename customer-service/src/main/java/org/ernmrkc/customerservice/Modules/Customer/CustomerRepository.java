package org.ernmrkc.customerservice.Modules.Customer;

import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Modules.Customer.Models.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    @Query("SELECT new org.ernmrkc.customerservice.Modules.Customer.Models.CustomerDTO(c.id, c.username, c.firstName) FROM Customer c")
    List<CustomerDTO> getAllCustomerDTOs();

}
