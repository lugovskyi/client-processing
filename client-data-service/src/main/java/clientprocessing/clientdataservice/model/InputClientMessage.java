package clientprocessing.clientdataservice.model;

import lombok.Builder;

@Builder
public record InputClientMessage(
        String sid,
        ClientDto clientDto) {
}
