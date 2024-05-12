package org.ernmrkc.customerservice.Modules.Address.QueryHandlers;

import org.ernmrkc.customerservice.Modules.Address.AddressRepository;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAddressesQueryHandler implements Query<Void, List<Address>> {
    private final AddressRepository addressRepository;

    public GetAllAddressesQueryHandler(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<List<Address>> execute(Void input) {
        List<Address> addressList = addressRepository.findAll();
        return ResponseEntity.ok().body(addressList);
    }
}
