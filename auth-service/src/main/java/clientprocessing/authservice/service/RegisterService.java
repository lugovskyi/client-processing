package clientprocessing.authservice.service;

import clientprocessing.authservice.model.ClientDto;
import clientprocessing.authservice.model.ClientResponseDto;
import clientprocessing.authservice.model.Message;
import clientprocessing.authservice.service.external.RabbitMqProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final RabbitMqProvider rabbitMqProvider;

    public ClientResponseDto registerClient(ClientDto clientDto) {
        log.info("client data: {}", clientDto);

        Message message = Message.builder().clientDto(clientDto)
                .sid(MDC.get("sid")).build();

        try {
            rabbitMqProvider.publishMessage(message);
        } catch (Exception e) {
            log.error("Error during message sending: {}", e.getMessage());
            throw e;
        }

        return ClientResponseDto.builder()
                .errorCode("0")
                .errorMessage("Success")
                .sid(MDC.get("sid")).build();
    }
}
