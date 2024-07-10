package clientprocessing.fraudservice.model;

import lombok.Builder;

@Builder
public record ClientFetchedData(
        String id,
        String taxNumber,
        String address,
        String passport,
        Boolean isFop,
        Boolean isFraudAssigned
) {
}
