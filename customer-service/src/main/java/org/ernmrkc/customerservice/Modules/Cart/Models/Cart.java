package org.ernmrkc.customerservice.Modules.Cart.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(CartItem item){
        cartItems.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item){
        cartItems.remove(item);
        item.setCart(null);
    }

}
