package clientprocessing.fraudservice.service;

import clientprocessing.fraudservice.model.ClientFetchedData;
import clientprocessing.fraudservice.model.FraudInfo;
import clientprocessing.fraudservice.model.InputClientMessage;
import clientprocessing.fraudservice.model.OutputClientMessage;
import clientprocessing.fraudservice.service.external.FraudMockedService;
import clientprocessing.fraudservice.service.external.RabbitMqProvider;
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
    private final FraudMockedService fraudMockedService;
    private final RabbitMqProvider rabbitMqProvider;
    private final ObjectMapper objectMapper;

    public void processMessage(String message) throws JsonProcessingException {
        InputClientMessage inputClientMessage = objectMapper.readValue(message, InputClientMessage.class);

        MDC.put("sid", inputClientMessage.sid());
        log.info("received message: {}", message);

        FraudInfo fraudInfo = fraudMockedService.findClientInfo(inputClientMessage.client().taxNumber());

        log.info("fraud service response {}", fraudInfo);

        OutputClientMessage outputClientMessage = OutputClientMessage.builder()
                .sid(inputClientMessage.sid())
                .client(ClientFetchedData.builder()
                        .id(inputClientMessage.client().id())
                        .taxNumber(inputClientMessage.client().taxNumber())
                        .passport(inputClientMessage.client().passport())
                        .address(inputClientMessage.client().address())
                        .isFop(inputClientMessage.client().isFop())
                        .isFraudAssigned(fraudInfo.isFraudAssigned()).build()).build();

        rabbitMqProvider.publishMessage(outputClientMessage);
    }
}
