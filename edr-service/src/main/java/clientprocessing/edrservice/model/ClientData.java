package clientprocessing.edrservice.model;

import lombok.Builder;

@Builder
public record ClientData(
        String id,
        String taxNumber,
        String address,
        String passport
) {
}
