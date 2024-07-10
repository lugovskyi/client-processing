package clientprocessing.clientdataservice.repository;


import clientprocessing.clientdataservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
