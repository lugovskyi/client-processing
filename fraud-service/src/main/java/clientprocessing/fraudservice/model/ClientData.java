package clientprocessing.fraudservice.model;

import lombok.Builder;

@Builder
public record ClientData(
        String id,
        String taxNumber,
        String address,
        String passport,
        Boolean isFop
) {
}
