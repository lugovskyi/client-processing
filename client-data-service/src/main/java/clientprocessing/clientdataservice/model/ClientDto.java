package clientprocessing.clientdataservice.model;

import lombok.Builder;

@Builder
public record ClientDto(
        String id,
        String taxNumber,
        String address,
        String passport,
        Boolean isFop,
        Boolean isFraudAssigned
) {
}
