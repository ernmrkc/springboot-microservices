package org.ernmrkc.customerservice.Modules.Cart.QueryHandlers;

import org.ernmrkc.customerservice.Modules.Cart.CartRepository;
import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.ernmrkc.customerservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCartsQueryHandlers implements Query<Void, List<Cart>> {
    private final CartRepository cartRepository;

    public GetAllCartsQueryHandlers(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ResponseEntity<List<Cart>> execute(Void input) {
        List<Cart> cartList = cartRepository.findAll();
        return ResponseEntity.ok().body(cartList);
    }
}
