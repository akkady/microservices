package me.akkad.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {
}