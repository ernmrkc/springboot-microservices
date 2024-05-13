package org.ernmrkc.orderservice.Modules.OrderItem;

import jakarta.validation.Valid;
import org.ernmrkc.orderservice.Modules.OrderItem.CommandHandlers.CreateOrderItemQueryHandler;
import org.ernmrkc.orderservice.Modules.OrderItem.Models.OrderItem;
import org.ernmrkc.orderservice.Modules.OrderItem.QueryHandlers.GetAllOrderItemsQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {
    private final CreateOrderItemQueryHandler createOrderItemQueryHandler;
    private final GetAllOrderItemsQueryHandler getAllOrderItemsQueryHandler;

    public OrderItemController(CreateOrderItemQueryHandler createOrderItemQueryHandler,
                               GetAllOrderItemsQueryHandler getAllOrderItemsQueryHandler) {
        this.createOrderItemQueryHandler = createOrderItemQueryHandler;
        this.getAllOrderItemsQueryHandler = getAllOrderItemsQueryHandler;
    }

    @GetMapping
    public ResponseEntity<List<OrderItem>> getAllOrderItems(){
        return getAllOrderItemsQueryHandler.execute(null);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@Valid @RequestBody OrderItem orderItem, BindingResult bindingResult){
        return createOrderItemQueryHandler.execute(orderItem, bindingResult);
    }
}
