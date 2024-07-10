package clientprocessing.edrservice.service.external;

import clientprocessing.edrservice.exception.ExternalServiceCallException;
import clientprocessing.edrservice.model.EdrInfo;
import org.springframework.stereotype.Service;

@Service
public class EdrMockedServiceImpl implements EdrMockedService {
    @Override
    public EdrInfo findClientInfo(String taxNumber) {
        if (taxNumber.startsWith("0")) {
            return EdrInfo.builder()
                    .isFop(true).build();
        } else if (taxNumber.startsWith("1")) {
            return EdrInfo.builder().isFop(false).build();
        } else throw new ExternalServiceCallException("Error during Diia api request..");
    }
}
