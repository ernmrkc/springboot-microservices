package org.ernmrkc.customerservice.Services.Customer_Address;

import org.ernmrkc.customerservice.Exceptions.AddressNotFoundException;
import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerAddressCommandService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public CustomerAddressCommandService(CustomerRepository customerRepository,
                                         AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    // TODO: JavaDoc - Check extra repository operations
    public ResponseEntity<Customer> addAddressToCustomer(UUID addressId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = authentication.getName();
        Optional<Customer> authenticatedOptionalCustomer = customerRepository.findByUsername(authenticatedUsername);
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (authenticatedOptionalCustomer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        if (optionalAddress.isEmpty()){
            throw new AddressNotFoundException();
        }

        Customer customer = authenticatedOptionalCustomer.get();
        Address address = optionalAddress.get();

        customer.getAddresses().add(address);
        address.getCustomers().add(customer);

        customerRepository.save(customer);
        addressRepository.save(address);
        return ResponseEntity.ok().body(customer);
    }
}
