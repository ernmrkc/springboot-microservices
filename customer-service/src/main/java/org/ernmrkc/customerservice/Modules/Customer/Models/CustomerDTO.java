package org.ernmrkc.customerservice.Modules.Customer.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private UUID id;
    private String username;
    private String firstName;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.username = customer.getUsername();
        this.firstName = customer.getFirstName();
    }
}
