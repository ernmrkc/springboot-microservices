package org.ernmrkc.customerservice.Modules.Customer.QueryHandlers;

import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.CustomerDTO;
import org.ernmrkc.customerservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCustomerDTOsQueryHandler implements Query <Void, List<CustomerDTO>> {
    private final CustomerRepository customerRepository;

    public GetAllCustomerDTOsQueryHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> execute(Void input) {
        List<CustomerDTO> customerDTOList = customerRepository.getAllCustomerDTOs();
        return ResponseEntity.ok().body(customerDTOList);
    }
}
