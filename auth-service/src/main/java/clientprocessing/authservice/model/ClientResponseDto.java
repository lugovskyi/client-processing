package clientprocessing.authservice.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ClientResponseDto(
        String sid,
        String errorCode,
        String errorMessage,
        LocalDateTime timestamp
) {
}
