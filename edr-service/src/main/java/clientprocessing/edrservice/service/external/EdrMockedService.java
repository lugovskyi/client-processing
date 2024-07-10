package clientprocessing.edrservice.service.external;

import clientprocessing.edrservice.model.EdrInfo;

public interface EdrMockedService {

    EdrInfo findClientInfo(String taxNumber);

}
