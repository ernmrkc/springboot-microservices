package org.ernmrkc.customerservice.Modules.Address.CommandHandlers;

import org.ernmrkc.customerservice.Exceptions.AddressNotFoundException;
import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteAddressCommandHandler implements Command<UUID, Void, Void> {
    private final AddressRepository addressRepository;

    public DeleteAddressCommandHandler(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<Void> execute(UUID id, Void bindingResult) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            throw new AddressNotFoundException();
        }
        Address address = optionalAddress.get();
        addressRepository.delete(address);
        return ResponseEntity.ok().build();

    }
}
