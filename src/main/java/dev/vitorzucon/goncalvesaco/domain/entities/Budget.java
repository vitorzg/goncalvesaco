package dev.vitorzucon.goncalvesaco.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Budget(
    Long id,
                String clientName,
                String clientEmail,
                int clientDoc,
                int clientPhone,
                String clientAddress,
                LocalDateTime createdAt,
                LocalDateTime completedAt,
                int status,
                BigDecimal totalPrice,
                String obs,
                List<ProductInCart> productList,
                int payment) {

}
