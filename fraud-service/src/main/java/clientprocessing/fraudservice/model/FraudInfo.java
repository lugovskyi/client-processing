package clientprocessing.fraudservice.model;

import lombok.Builder;

@Builder
public record FraudInfo(Boolean isFraudAssigned) {
}
