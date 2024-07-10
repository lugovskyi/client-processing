package clientprocessing.edrservice.model;

import lombok.Builder;

@Builder
public record InputClientMessage(
        String sid,
        ClientData client
) {
}
