package clientprocessing.diiaservice.service.external;

import clientprocessing.diiaservice.model.DiiaInfo;

public interface DiiaMockedService {

    DiiaInfo findClientInfo(String taxNumber);

}
