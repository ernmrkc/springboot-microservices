package org.ernmrkc.customerservice.Modules.CartItem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;

import java.util.UUID;

@Entity
@Data
@Table(name = "Cart_Item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_item_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @NotNull(message = "Product id cannot be empty")
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 0, message = "Quantity must be more than 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Price per item cannot be empty")
    @Column(name = "price_per_item", nullable = false)
    private Double pricePerItem;
}
