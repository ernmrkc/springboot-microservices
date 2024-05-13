package org.ernmrkc.orderservice.Modules.OrderItem.CommandHandlers;

import org.ernmrkc.orderservice.Modules.Command;
import org.ernmrkc.orderservice.Modules.OrderItem.Models.OrderItem;
import org.ernmrkc.orderservice.Modules.OrderItem.OrderItemRepository;
import org.ernmrkc.orderservice.Validations.OrderItemValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CreateOrderItemQueryHandler implements Command<OrderItem, BindingResult, OrderItem> {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemValidationService orderItemValidationService;

    public CreateOrderItemQueryHandler(OrderItemRepository orderItemRepository,
                                       OrderItemValidationService orderItemValidationService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemValidationService = orderItemValidationService;
    }

    @Override
    public ResponseEntity<OrderItem> execute(OrderItem orderItem, BindingResult bindingResult) {
        orderItemValidationService.validateOrderItem(orderItem, bindingResult);
        orderItemRepository.save(orderItem);
        return ResponseEntity.ok().body(orderItem);
    }
}
