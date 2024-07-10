package clientprocessing.fraudservice.service.external;


import clientprocessing.fraudservice.model.FraudInfo;

public interface FraudMockedService {

    FraudInfo findClientInfo(String taxNumber);

}
