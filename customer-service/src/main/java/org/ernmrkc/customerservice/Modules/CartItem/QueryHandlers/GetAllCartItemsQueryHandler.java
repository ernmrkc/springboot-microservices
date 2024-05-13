package org.ernmrkc.customerservice.Modules.CartItem.QueryHandlers;

import org.ernmrkc.customerservice.Modules.CartItem.CartItemRepository;
import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.ernmrkc.customerservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllCartItemsQueryHandler implements Query<Void, List<CartItem>> {
    private final CartItemRepository cartItemRepository;

    public GetAllCartItemsQueryHandler(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public ResponseEntity<List<CartItem>> execute(Void input) {
        List<CartItem> cartItemList = cartItemRepository.findAll();
        return ResponseEntity.ok().body(cartItemList);
    }
}
