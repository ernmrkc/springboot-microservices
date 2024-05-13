package org.ernmrkc.customerservice.Modules.CartItem;

import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
