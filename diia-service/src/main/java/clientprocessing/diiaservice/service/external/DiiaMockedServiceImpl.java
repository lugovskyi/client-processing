package clientprocessing.diiaservice.service.external;

import clientprocessing.diiaservice.exception.ExternalServiceCallException;
import clientprocessing.diiaservice.model.DiiaInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiiaMockedServiceImpl implements DiiaMockedService {
    @Override
    public DiiaInfo findClientInfo(String taxNumber) {
        log.info("Requesting Diia info by tax number: {}", taxNumber);

        if (taxNumber.startsWith("0")) {
            return DiiaInfo.builder()
                    .address("Льотна, 50")
                    .passport("ПС987234").build();
        } else if (taxNumber.startsWith("7")) {
            return DiiaInfo.builder()
                    .address("Шевченка, 100")
                    .passport("ПМ90050").build();
        }
        if (taxNumber.startsWith("1")) {
            throw new ExternalServiceCallException("Error during Diia api request..");
        } else {
            return DiiaInfo.builder()
                    .address("Героїв УПА, 12")
                    .passport("ПМ123456").build();
        }
    }
}
