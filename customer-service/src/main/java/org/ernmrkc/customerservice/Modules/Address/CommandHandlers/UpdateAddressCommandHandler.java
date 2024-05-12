package org.ernmrkc.customerservice.Modules.Address.CommandHandlers;

import org.ernmrkc.customerservice.Exceptions.AddressNotFoundException;
import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Address.Models.UpdateAddressCommand;
import org.ernmrkc.customerservice.Modules.Command;
import org.ernmrkc.customerservice.Validations.AddressValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UpdateAddressCommandHandler implements Command<UpdateAddressCommand, BindingResult, Address> {
    private final AddressRepository addressRepository;
    private final AddressValidationService addressValidationService;

    public UpdateAddressCommandHandler(AddressRepository addressRepository,
                                       AddressValidationService addressValidationService) {
        this.addressRepository = addressRepository;
        this.addressValidationService = addressValidationService;
    }

    @Override
    public ResponseEntity<Address> execute(UpdateAddressCommand updateAddressCommand, BindingResult bindingResult) {
        Optional<Address> optionalAddress = addressRepository.findById(updateAddressCommand.getId());
        if (optionalAddress.isEmpty()){
            throw new AddressNotFoundException();
        }
        addressValidationService.validateAddress(updateAddressCommand.getAddress(), bindingResult);
        Address address = updateAddressCommand.getAddress();
        address.setId(updateAddressCommand.getId());
        addressRepository.save(address);
        return ResponseEntity.ok().body(address);
    }
}
