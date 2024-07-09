package clientprocessing.authservice.controller.impl;

import clientprocessing.authservice.controller.ApiController;
import clientprocessing.authservice.model.ClientDto;
import clientprocessing.authservice.model.ClientResponseDto;
import clientprocessing.authservice.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiControllerImpl implements ApiController {
    private final RegisterService registerService;

    @Override
    public ClientResponseDto registerClient(ClientDto clientDto) {
        return registerService.registerClient(clientDto);
    }
}
