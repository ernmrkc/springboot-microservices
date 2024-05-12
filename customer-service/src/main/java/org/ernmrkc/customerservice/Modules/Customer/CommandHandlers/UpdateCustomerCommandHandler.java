package org.ernmrkc.customerservice.Modules.Customer.CommandHandlers;

import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Modules.Command;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Modules.Customer.Models.UpdateCustomerCommand;
import org.ernmrkc.customerservice.Validations.CustomerValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UpdateCustomerCommandHandler implements Command<UpdateCustomerCommand, BindingResult, Customer> {
    private final CustomerRepository customerRepository;
    private final CustomerValidationService customerValidationService;

    public UpdateCustomerCommandHandler(CustomerRepository customerRepository,
                                        CustomerValidationService customerValidationService) {
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
    }

    @Override
    public ResponseEntity<Customer> execute(UpdateCustomerCommand updateCustomerCommand, BindingResult bindingResult) {
        Optional<Customer> optionalCustomer = customerRepository.findById(updateCustomerCommand.getId());
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        customerValidationService.validateCustomer(updateCustomerCommand.getCustomer(), bindingResult);
        Customer customer = updateCustomerCommand.getCustomer();
        customer.setId(updateCustomerCommand.getId());
        customer.setRegistrationDate(optionalCustomer.get().getRegistrationDate());
        customerRepository.save(customer);
        return ResponseEntity.ok().body(customer);
    }
}
