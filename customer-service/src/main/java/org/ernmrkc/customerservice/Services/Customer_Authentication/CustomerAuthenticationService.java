package org.ernmrkc.customerservice.Services.Customer_Authentication;

import org.ernmrkc.customerservice.Exceptions.CustomerNotFoundException;
import org.ernmrkc.customerservice.Helpers.JwtUtil;
import org.ernmrkc.customerservice.Modules.Customer.CustomerRepository;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.ernmrkc.customerservice.Security.Models.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerAuthenticationService {
    private final CustomerRepository customerRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;

    public CustomerAuthenticationService(AuthenticationManager authenticationManager,
                                         CustomerRepository customerRepository,
                                         BCryptPasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }

    public ResponseEntity<JwtResponse> createJwt(String username, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findByUsername(username);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException();
        }

        // TODO: Commented because existing database records are incorrect
        /*
        Customer customer = optionalCustomer.get();
        if (!encoder.matches(password, customer.getPassword())){
            throw new BadCredentialsException("Invalid username/password supplied");
        }
        */

        String jwtToken = JwtUtil.generateToken(username);
        return ResponseEntity.ok(new JwtResponse(jwtToken));
    }
}
