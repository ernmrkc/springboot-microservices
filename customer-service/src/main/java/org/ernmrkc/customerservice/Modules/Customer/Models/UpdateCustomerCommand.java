package org.ernmrkc.customerservice.Modules.Customer.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateCustomerCommand {
    private UUID id;
    private Customer customer;

    public UpdateCustomerCommand(UUID id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }
}
