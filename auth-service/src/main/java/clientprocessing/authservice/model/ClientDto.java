package clientprocessing.authservice.model;

public record ClientDto(
        String id,
        String passport,
        String taxNumber
) {
}
