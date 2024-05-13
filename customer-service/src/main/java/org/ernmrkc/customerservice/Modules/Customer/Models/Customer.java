package org.ernmrkc.customerservice.Modules.Customer.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    private UUID id;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 30, message = "Username must not exceed 30 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, message = "Password must consist of at least 5 characters ")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "First name cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Surname cannot be empty")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Size(max = 50, message = "Phone number must not exceed 50 characters")
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDate registrationDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Cart cart;

    public void setCart(Cart cart){
        this.cart = cart;
        cart.setCustomer(this);
    }
}
