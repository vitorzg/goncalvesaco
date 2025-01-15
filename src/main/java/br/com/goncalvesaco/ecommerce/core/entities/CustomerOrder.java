package br.com.goncalvesaco.ecommerce.core.entities;

import java.util.Date;
import java.util.List;

public record CustomerOrder(
        String customer_name,
        String customer_phone,
        String customer_address,
        List<Product> product_list,
        PaymentMethod payment_method,
        float total_price,
        String obs,
        OrderStatus order_status,
        Date created_at,
        Date updated_at
) {
}
