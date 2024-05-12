package org.ernmrkc.customerservice.Modules.Customer.CommandHandlers;

import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Modules.Command;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteCustomerCommandHandler implements Command<UUID, Void, Void> {
    private final CustomerRepository customerRepository;

    public DeleteCustomerCommandHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseEntity<Void> execute(UUID id, Void bindingResult) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        Customer customer = optionalCustomer.get();
        customerRepository.delete(customer);
        return ResponseEntity.ok().build();
    }
}
