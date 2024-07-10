package clientprocessing.clientdataservice.service;

import clientprocessing.clientdataservice.model.InputClientMessage;
import clientprocessing.clientdataservice.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessingService {
    private final ObjectMapper objectMapper;
    private final ClientRepository repository;

    public void processMessage(String message) throws JsonProcessingException {
        InputClientMessage inputClientMessage = objectMapper.readValue(message, InputClientMessage.class);
        log.info("saving to db..");

    }
}
