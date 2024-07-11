package clientprocessing.edrservice.service.external;

import clientprocessing.edrservice.exception.ExternalServiceCallException;
import clientprocessing.edrservice.model.EdrInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EdrMockedServiceImpl implements EdrMockedService {
    @Override
    public EdrInfo findClientInfo(String taxNumber) {
        log.info("edr service call ...");

        if (taxNumber.startsWith("0")) {
            return EdrInfo.builder()
                    .isFop(true).build();
        } else if (taxNumber.startsWith("7")) {
            return EdrInfo.builder().isFop(false).build();
        } else if (taxNumber.startsWith("2")) {
            throw new ExternalServiceCallException("Error during Diia api request..");
        } else {
            return EdrInfo.builder()
                    .isFop(true).build();
        }
    }
}
