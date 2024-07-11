package clientprocessing.clientdataservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "client")
public class Client {
    @Id
    Long id;
    String clientId;
    String taxNumber;
    String address;
    String passport;
    Boolean isFop;
    Boolean isFraudAssigned;
}
