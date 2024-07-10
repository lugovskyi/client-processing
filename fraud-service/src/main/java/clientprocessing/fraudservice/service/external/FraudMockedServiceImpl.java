package clientprocessing.fraudservice.service.external;

import clientprocessing.fraudservice.exception.ExternalServiceCallException;
import clientprocessing.fraudservice.model.FraudInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FraudMockedServiceImpl implements FraudMockedService {

    @Override
    public FraudInfo findClientInfo(String taxNumber) {
        log.info("fraud service call ...");

        if (taxNumber.startsWith("1")) {
            return FraudInfo.builder()
                    .isFraudAssigned(true).build();

        } else if (taxNumber.startsWith("7")) {
            return FraudInfo.builder().isFraudAssigned(false).build();

        } else
            throw new ExternalServiceCallException("Error during Diia api request..");
    }
}
