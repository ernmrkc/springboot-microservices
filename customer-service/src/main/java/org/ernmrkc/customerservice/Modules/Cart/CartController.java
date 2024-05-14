package org.ernmrkc.customerservice.Modules.Cart;

import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.ernmrkc.customerservice.Modules.Cart.QueryHandlers.GetAllCartsQueryHandlers;
import org.ernmrkc.customerservice.Services.Cart_Customer.CartCustomerCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetAllCartsQueryHandlers getAllCartsQueryHandlers;
    private final CartCustomerCommandService cartCustomerCommandService;


    public CartController(GetAllCartsQueryHandlers getAllCartsQueryHandlers,
                          CartCustomerCommandService cartCustomerCommandService) {
        this.getAllCartsQueryHandlers = getAllCartsQueryHandlers;
        this.cartCustomerCommandService = cartCustomerCommandService;
    }

    @GetMapping
    ResponseEntity<List<Cart>> getAllCarts(){
        return getAllCartsQueryHandlers.execute(null);
    }

    @PostMapping
    ResponseEntity<Cart> createCart(@RequestParam(value = "customer_id") UUID customerId){
        return cartCustomerCommandService.createCartForCustomer(customerId);
    }
}
