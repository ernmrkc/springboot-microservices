package org.ernmrkc.orderservice.Modules.OrderItem.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.ernmrkc.orderservice.Modules.OrderRecord.Models.OrderRecord;

import java.util.UUID;

@Entity
@Data
@Table(name = "Order_Item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_item_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord orderRecord;

    @NotNull(message = "Product Id cannot be empty")
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 0, message = "Quantity must be more than 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price_per_item", nullable = false)
    private Double pricePerItem;

    public Double getTotalPrice(){
        return pricePerItem * quantity;
    }
}
