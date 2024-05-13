package org.ernmrkc.orderservice.Modules.OrderItem.QueryHandlers;

import org.ernmrkc.orderservice.Modules.OrderItem.Models.OrderItem;
import org.ernmrkc.orderservice.Modules.OrderItem.OrderItemRepository;
import org.ernmrkc.orderservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrderItemsQueryHandler implements Query<Void, List<OrderItem>> {
    private final OrderItemRepository orderItemRepository;

    public GetAllOrderItemsQueryHandler(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public ResponseEntity<List<OrderItem>> execute(Void input) {
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        return ResponseEntity.ok().body(orderItemList);
    }
}
