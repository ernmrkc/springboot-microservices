package org.ernmrkc.customerservice.Modules.Address;

import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
