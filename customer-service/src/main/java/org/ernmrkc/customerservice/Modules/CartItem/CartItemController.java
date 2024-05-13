package org.ernmrkc.customerservice.Modules.CartItem;

import jakarta.validation.Valid;
import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.ernmrkc.customerservice.Services.CartItem_Cart.CartItemCartCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart-item")
public class CartItemController {
    private final CartItemCartCommandService cartItemCartCommandService;

    public CartItemController(CartItemCartCommandService cartItemCartCommandService) {
        this.cartItemCartCommandService = cartItemCartCommandService;
    }

    @PostMapping
    public ResponseEntity<CartItem> createCartItem(@RequestParam(value = "id") UUID cartId, @Valid @RequestBody CartItem cartItem, BindingResult bindingResult){
        return cartItemCartCommandService.addCartItemToCart(cartId, cartItem, bindingResult);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCartItem(@RequestParam(value = "id") UUID cartItemId){
        return cartItemCartCommandService.deleteCartItem(cartItemId);
    }

    @PutMapping
    public ResponseEntity<CartItem> updateCartItem(@RequestParam(value = "id") UUID cartItemId, @RequestParam(value = "quantity") Integer newQuantity){
        return cartItemCartCommandService.updateCartItemQuantity(cartItemId, newQuantity);
    }
}
