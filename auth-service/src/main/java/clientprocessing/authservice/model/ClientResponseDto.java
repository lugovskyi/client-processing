package clientprocessing.authservice.model;

import lombok.Builder;

@Builder
public record ClientResponseDto(
        String sid,
        String errorCode,
        String errorMessage
) {
}
