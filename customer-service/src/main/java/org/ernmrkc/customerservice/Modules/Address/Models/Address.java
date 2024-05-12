package org.ernmrkc.customerservice.Modules.Address.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID id;

    @NotBlank(message = "Country cannot be empty")
    @Size(max = 20, message = "Country must not exceed 20 characters")
    @Column(name = "country", length = 20)
    private String country;

    @NotBlank(message = "City cannot be empty")
    @Size(max = 20, message = "City must not exceed 20 characters")
    @Column(name = "city", length = 20)
    private String city;

    @NotBlank(message = "District cannot be empty")
    @Size(max = 50, message = "District must not exceed 50 characters")
    @Column(name = "district", length = 50)
    private String district;

    @NotBlank(message = "Neighborhood cannot be empty")
    @Size(max = 100, message = "Neighborhood must not exceed 100 characters")
    @Column(name = "neighborhood", length = 100)
    private String neighborhood;

    @NotBlank(message = "Street cannot be empty")
    @Size(max = 100, message = "Street must not exceed 100 characters")
    @Column(name = "street", length = 100)
    private String street;

    @NotBlank(message = "Door number cannot be empty")
    @Size(max = 20, message = "Door number must not exceed 20 characters")
    @Column(name = "door_number", length = 20)
    private String doorNumber;

    @ManyToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customer> customers;
}
