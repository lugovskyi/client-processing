package clientprocessing.authservice.controller;

import clientprocessing.authservice.model.ClientDto;
import clientprocessing.authservice.model.ClientResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
public interface ApiController {

    @PostMapping("/register")
    ClientResponseDto registerClient(@RequestBody ClientDto clientDto);
}
