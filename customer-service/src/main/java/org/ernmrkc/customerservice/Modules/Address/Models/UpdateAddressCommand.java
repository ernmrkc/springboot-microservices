package org.ernmrkc.customerservice.Modules.Address.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateAddressCommand {
    private UUID id;
    private Address address;

    public UpdateAddressCommand(UUID id, Address address) {
        this.id = id;
        this.address = address;
    }
}
