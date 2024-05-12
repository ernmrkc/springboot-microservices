package org.ernmrkc.customerservice.Modules.Customer.QueryHandlers;

import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCustomersQueryHandler implements Query<Void, List<Customer>> {
    private final CustomerRepository customerRepository;

    public GetAllCustomersQueryHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<List<Customer>> execute(Void input) {
        List<Customer> customerList = customerRepository.findAll();
        return ResponseEntity.ok().body(customerList);
    }
}
