package clientprocessing.authservice.model;

public record ClientResponseDto(
        String id,
        String passport,
        String taxNumber,
        String errorCode,
        String errorMessage
) {
}
