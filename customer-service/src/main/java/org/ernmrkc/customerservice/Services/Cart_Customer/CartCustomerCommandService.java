package org.ernmrkc.customerservice.Services.Cart_Customer;

import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Modules.Cart.CartRepository;
import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartCustomerCommandService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CartCustomerCommandService(CartRepository cartRepository,
                                      CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<Cart> createCartForCustomer(UUID customerId){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException();
        }
        Customer customer = optionalCustomer.get();
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartRepository.save(cart);
        return ResponseEntity.ok().body(cart);
    }
}
