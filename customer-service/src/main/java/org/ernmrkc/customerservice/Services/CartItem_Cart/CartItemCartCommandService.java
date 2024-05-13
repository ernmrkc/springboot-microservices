package org.ernmrkc.customerservice.Services.CartItem_Cart;

import org.ernmrkc.customerservice.Exceptions.CartItemNotFoundException;
import org.ernmrkc.customerservice.Exceptions.CartNotFoundException;
import org.ernmrkc.customerservice.Modules.Cart.CartRepository;
import org.ernmrkc.customerservice.Modules.Cart.Models.Cart;
import org.ernmrkc.customerservice.Modules.CartItem.CartItemRepository;
import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.ernmrkc.customerservice.Validations.CartItemValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemCartCommandService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemValidationService cartItemValidationService;
    public CartItemCartCommandService(CartRepository cartRepository,
                                      CartItemRepository cartItemRepository,
                                      CartItemValidationService cartItemValidationService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemValidationService = cartItemValidationService;
    }

    // TODO: JavaDoc
    public ResponseEntity<CartItem> addCartItemToCart(UUID cartId, CartItem cartItem, BindingResult bindingResult){
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()){
            throw new CartNotFoundException();
        }
        cartItemValidationService.validateCartItem(cartItem, bindingResult);
        Cart cart = optionalCart.get();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok().body(cartItem);
    }

    // TODO: JavaDoc
    public ResponseEntity<Void> deleteCartItem(UUID cartItemId){
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isEmpty()){
            throw new CartItemNotFoundException();
        }
        CartItem cartItem = optionalCartItem.get();
        cartItemRepository.delete(cartItem);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<CartItem> updateCartItemQuantity(UUID cartItemId, Integer newQuantity){
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isEmpty()){
            throw new CartItemNotFoundException();
        }
        CartItem cartItem = optionalCartItem.get();
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
        return ResponseEntity.ok().body(cartItem);
    }
}
