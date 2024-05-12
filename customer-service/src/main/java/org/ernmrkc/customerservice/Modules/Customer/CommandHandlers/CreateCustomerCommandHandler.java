package org.ernmrkc.customerservice.Modules.Customer.CommandHandlers;

import org.ernmrkc.customerservice.Modules.Command;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Modules.Customer.Models.CustomerDTO;
import org.ernmrkc.customerservice.Validations.CustomerValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;

@Service
public class CreateCustomerCommandHandler implements Command<Customer, BindingResult, CustomerDTO> {
    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;

    public CreateCustomerCommandHandler(CustomerRepository customerRepository,
                                        CustomerValidationService customerValidationService) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
    }

    @Override
    public ResponseEntity<CustomerDTO> execute(Customer customer, BindingResult bindingResult) {
        customerValidationService.validateCustomer(customer, bindingResult);
        customerRepository.save(customer);
        return ResponseEntity.ok().body(new CustomerDTO(customer));
    }
}
