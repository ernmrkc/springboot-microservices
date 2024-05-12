package org.ernmrkc.customerservice.Modules.Address.CommandHandlers;

import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Command;
import org.ernmrkc.customerservice.Validations.AddressValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class CreateAddressCommandHandler implements Command<Address, BindingResult, Address> {
    private final AddressRepository addressRepository;
    private final AddressValidationService addressValidationService;

    public CreateAddressCommandHandler(AddressRepository addressRepository,
                                       AddressValidationService addressValidationService) {
        this.addressRepository = addressRepository;
        this.addressValidationService = addressValidationService;
    }

    @Override
    public ResponseEntity<Address> execute(Address address, BindingResult bindingResult) {
        addressValidationService.validateAddress(address, bindingResult);
        addressRepository.save(address);
        return ResponseEntity.ok().body(address);
    }
}
