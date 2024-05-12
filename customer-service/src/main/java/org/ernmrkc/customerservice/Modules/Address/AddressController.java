package org.ernmrkc.customerservice.Modules.Address;

import jakarta.validation.Valid;
import org.ernmrkc.customerservice.Modules.Address.CommandHandlers.CreateAddressCommandHandler;
import org.ernmrkc.customerservice.Modules.Address.CommandHandlers.DeleteAddressCommandHandler;
import org.ernmrkc.customerservice.Modules.Address.CommandHandlers.UpdateAddressCommandHandler;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Address.Models.UpdateAddressCommand;
import org.ernmrkc.customerservice.Modules.Address.QueryHandlers.GetAllAddressesQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final CreateAddressCommandHandler createAddressCommandHandler;
    private final DeleteAddressCommandHandler deleteAddressCommandHandler;
    private final UpdateAddressCommandHandler updateAddressCommandHandler;
    private final GetAllAddressesQueryHandler getAllAddressesQueryHandler;

    public AddressController(CreateAddressCommandHandler createAddressCommandHandler,
                             DeleteAddressCommandHandler deleteAddressCommandHandler,
                             UpdateAddressCommandHandler updateAddressCommandHandler,
                             GetAllAddressesQueryHandler getAllAddressesQueryHandler) {
        this.createAddressCommandHandler = createAddressCommandHandler;
        this.deleteAddressCommandHandler = deleteAddressCommandHandler;
        this.updateAddressCommandHandler = updateAddressCommandHandler;
        this.getAllAddressesQueryHandler = getAllAddressesQueryHandler;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return getAllAddressesQueryHandler.execute(null);
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address, BindingResult bindingResult) {
        return createAddressCommandHandler.execute(address, bindingResult);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAddress(@RequestParam(value = "id") UUID id) {
        return deleteAddressCommandHandler.execute(id, null);
    }

    @PutMapping
    public ResponseEntity<Address> updateAddress(@RequestParam(value = "id") UUID id, @Valid @RequestBody Address address, BindingResult bindingResult){
        UpdateAddressCommand updateAddressCommand = new UpdateAddressCommand(id, address);
        return updateAddressCommandHandler.execute(updateAddressCommand, bindingResult);
    }
}
