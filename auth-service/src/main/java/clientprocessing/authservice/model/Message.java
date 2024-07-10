package clientprocessing.authservice.model;

import lombok.Builder;

@Builder
public record Message(
        String sid,
        ClientDto client) {
}
