package clientprocessing.fraudservice.model;

import lombok.Builder;

@Builder
public record OutputClientMessage(
        String sid,
        ClientFetchedData client
) {
}

