package clientprocessing.diiaservice.service;

import clientprocessing.diiaservice.model.ClientFetchedData;
import clientprocessing.diiaservice.model.DiiaInfo;
import clientprocessing.diiaservice.model.InputClientMessage;
import clientprocessing.diiaservice.model.OutputClientMessage;
import clientprocessing.diiaservice.service.external.DiiaMockedService;
import clientprocessing.diiaservice.service.external.RabbitMqProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingService {
    private final DiiaMockedService diiaMockedService;
    private final RabbitMqProvider rabbitMqProvider;
    private final ObjectMapper objectMapper;

    public void processMessage(String message) throws JsonProcessingException {
        InputClientMessage inputClientMessage = objectMapper.readValue(message, InputClientMessage.class);

        MDC.put("sid", inputClientMessage.sid());
        log.info("received message: {}", message);

        DiiaInfo diiaInfo = diiaMockedService.findClientInfo(inputClientMessage.client().taxNumber());

        OutputClientMessage outputClientMessage = OutputClientMessage.builder()
                .sid(inputClientMessage.sid())
                .client(ClientFetchedData.builder()
                        .id(inputClientMessage.client().id())
                        .taxNumber(inputClientMessage.client().taxNumber())
                        .address(diiaInfo.address())
                        .passport(diiaInfo.passport()).build()).build();

        rabbitMqProvider.publishMessage(outputClientMessage);
    }
}
