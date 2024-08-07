package clientprocessing.diiaservice.model;

import lombok.Builder;

@Builder
public record InputClientMessage(
        String sid,
        Client client) {
}
