package clientprocessing.authservice.controller;

import clientprocessing.authservice.model.ClientDto;
import clientprocessing.authservice.model.ClientResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public interface ApiController {

    @PostMapping("/register")
    ClientResponseDto registerClient(ClientDto clientDto);
}
