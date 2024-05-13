package org.ernmrkc.customerservice.Services.Customer_Address;

import org.ernmrkc.customerservice.Exceptions.AddressNotFoundException;
import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.springframework.http.ResponseEntity;
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

    // TODO: JavaDoc
    public ResponseEntity<Customer> addAddressToCustomer(UUID customerId, UUID addressId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        if (optionalAddress.isEmpty()){
            throw new AddressNotFoundException();
        }
        Customer customer = optionalCustomer.get();
        Address address = optionalAddress.get();

        customer.getAddresses().add(address);
        address.getCustomers().add(customer);

        customerRepository.save(customer);
        addressRepository.save(address);
        return ResponseEntity.ok().body(customer);
    }
}
