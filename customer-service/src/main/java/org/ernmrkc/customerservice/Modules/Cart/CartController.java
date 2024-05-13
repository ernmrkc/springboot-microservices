package org.ernmrkc.customerservice.Modules.Cart;

import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.ernmrkc.customerservice.Modules.Cart.QueryHandlers.GetAllCartsQueryHandlers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final GetAllCartsQueryHandlers getAllCartsQueryHandlers;

    public CartController(GetAllCartsQueryHandlers getAllCartsQueryHandlers) {
        this.getAllCartsQueryHandlers = getAllCartsQueryHandlers;
    }

    @GetMapping
    ResponseEntity<List<Cart>> getAllCarts(){
        return getAllCartsQueryHandlers.execute(null);
    }
}
