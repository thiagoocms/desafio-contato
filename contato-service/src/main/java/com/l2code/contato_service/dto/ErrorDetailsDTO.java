package com.l2code.contato_service.dto;

public record ErrorDetailsDTO(
        Long timestamp,
        int status,
        String message,
        String details
) {
}
