package clientprocessing.edrservice.service;

import clientprocessing.edrservice.model.ClientFetchedData;
import clientprocessing.edrservice.model.EdrInfo;
import clientprocessing.edrservice.model.InputClientMessage;
import clientprocessing.edrservice.model.OutputClientMessage;
import clientprocessing.edrservice.service.external.EdrMockedService;
import clientprocessing.edrservice.service.external.RabbitMqProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingService {
    private final EdrMockedService edrMockedService;
    private final RabbitMqProvider rabbitMqProvider;
    private final ObjectMapper objectMapper;

    public void processMessage(String message) throws JsonProcessingException {
        InputClientMessage inputClientMessage = objectMapper.readValue(message, InputClientMessage.class);

        EdrInfo edrInfo = edrMockedService.findClientInfo(inputClientMessage.client().taxNumber());

        OutputClientMessage outputClientMessage = OutputClientMessage.builder()
                .sid(inputClientMessage.sid())
                .client(ClientFetchedData.builder()
                        .id(inputClientMessage.client().id())
                        .taxNumber(inputClientMessage.client().taxNumber())
                        .passport(inputClientMessage.client().passport())
                        .address(inputClientMessage.client().address())
                        .isFop(edrInfo.isFop()).build()).build();

        rabbitMqProvider.publishMessage(outputClientMessage);
    }
}
