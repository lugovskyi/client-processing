package clientprocessing.clientdataservice.service;

import clientprocessing.clientdataservice.entity.Client;
import clientprocessing.clientdataservice.model.InputClientMessage;
import clientprocessing.clientdataservice.repository.ClientRepository;
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
    private final ObjectMapper objectMapper;
    private final ClientRepository repository;

    public void processMessage(String message) throws JsonProcessingException {

        InputClientMessage inputClientMessage = objectMapper.readValue(message, InputClientMessage.class);

        MDC.put("sid", inputClientMessage.sid());
        log.info("received message: {}", message);

        Client client = Client.builder()
                .taxNumber(inputClientMessage.client().taxNumber())
                .address(inputClientMessage.client().address())
                .passport(inputClientMessage.client().passport())
                .isFop(inputClientMessage.client().isFop())
                .isFraudAssigned(inputClientMessage.client().isFraudAssigned())
                .build();

        log.info("saving client {} to db..", client);

        repository.save(client);
    }
}
