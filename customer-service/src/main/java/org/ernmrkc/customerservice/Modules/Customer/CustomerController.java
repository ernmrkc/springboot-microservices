package org.ernmrkc.customerservice.Modules.Customer;

import jakarta.validation.Valid;
import org.ernmrkc.customerservice.Modules.Customer.CommandHandlers.CreateCustomerCommandHandler;
import org.ernmrkc.customerservice.Modules.Customer.CommandHandlers.DeleteCustomerCommandHandler;
import org.ernmrkc.customerservice.Modules.Customer.CommandHandlers.UpdateCustomerCommandHandler;
import org.ernmrkc.customerservice.Security.Models.JwtResponse;
import org.ernmrkc.customerservice.Security.Models.LoginRequest;
import org.ernmrkc.customerservice.Services.Customer_Address.CustomerAddressCommandService;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Modules.Customer.Models.CustomerDTO;
import org.ernmrkc.customerservice.Modules.Customer.Models.UpdateCustomerCommand;
import org.ernmrkc.customerservice.Modules.Customer.QueryHandlers.GetAllCustomerDTOsQueryHandler;
import org.ernmrkc.customerservice.Modules.Customer.QueryHandlers.GetAllCustomersQueryHandler;
import org.ernmrkc.customerservice.Services.Customer_Authentication.CustomerAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CreateCustomerCommandHandler createCustomerCommandHandler;
    private final DeleteCustomerCommandHandler deleteCustomerCommandHandler;
    private final UpdateCustomerCommandHandler updateCustomerCommandHandler;
    private final GetAllCustomersQueryHandler getAllCustomersQueryHandler;
    private final GetAllCustomerDTOsQueryHandler getAllCustomerDTOsQueryHandler;
    private final CustomerAddressCommandService customerAddressCommandService;
    private final CustomerAuthenticationService customerAuthenticationService;

    public CustomerController(CreateCustomerCommandHandler createCustomerCommandHandler,
                              DeleteCustomerCommandHandler deleteCustomerCommandHandler,
                              UpdateCustomerCommandHandler updateCustomerCommandHandler,
                              GetAllCustomersQueryHandler getAllCustomersQueryHandler,
                              GetAllCustomerDTOsQueryHandler getAllCustomerDTOsQueryHandler,
                              CustomerAddressCommandService customerAddressCommandService,
                              CustomerAuthenticationService customerAuthenticationService) {
        this.createCustomerCommandHandler = createCustomerCommandHandler;
        this.deleteCustomerCommandHandler = deleteCustomerCommandHandler;
        this.updateCustomerCommandHandler = updateCustomerCommandHandler;
        this.getAllCustomersQueryHandler = getAllCustomersQueryHandler;
        this.getAllCustomerDTOsQueryHandler = getAllCustomerDTOsQueryHandler;
        this.customerAddressCommandService = customerAddressCommandService;
        this.customerAuthenticationService = customerAuthenticationService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return getAllCustomersQueryHandler.execute(null);
    }

    @GetMapping("/DTOs")
    public ResponseEntity<List<CustomerDTO>> getAllCustomerDTOs(){
        return getAllCustomerDTOsQueryHandler.execute(null);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        return createCustomerCommandHandler.execute(customer, bindingResult);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createJwtResponse(@RequestBody LoginRequest loginRequest){
        return customerAuthenticationService.createJwt(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@RequestParam(value = "id") UUID id){
        return deleteCustomerCommandHandler.execute(id, null);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestParam(value = "id") UUID id, @Valid @RequestBody Customer customer, BindingResult bindingResult){
        UpdateCustomerCommand updateCustomerCommand = new UpdateCustomerCommand(id, customer);
        return updateCustomerCommandHandler.execute(updateCustomerCommand, bindingResult);
    }

    @PutMapping("/add-address/{addressId}")
    public ResponseEntity<Customer> addAddressToCustomer(@PathVariable UUID addressId){
        return customerAddressCommandService.addAddressToCustomer(addressId);
    }
}
