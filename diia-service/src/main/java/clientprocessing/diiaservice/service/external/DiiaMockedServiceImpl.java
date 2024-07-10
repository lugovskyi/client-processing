package clientprocessing.diiaservice.service.external;

import clientprocessing.diiaservice.exception.ExternalServiceCallException;
import clientprocessing.diiaservice.model.DiiaInfo;
import org.springframework.stereotype.Service;

@Service
public class DiiaMockedServiceImpl implements DiiaMockedService {
    @Override
    public DiiaInfo findClientInfo(String taxNumber) {
        if (taxNumber.startsWith("0")) {
            return DiiaInfo.builder()
                    .address("Льотна, 50")
                    .passport("ПС987234").build();
        } else if (taxNumber.startsWith("1")) {
            return DiiaInfo.builder()
                    .address("Шевченка, 100")
                    .passport("ПМ90050").build();
        } else throw new ExternalServiceCallException("Error during Diia api request..");
    }
}
