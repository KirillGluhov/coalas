package patterns.users.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patterns.users.data.entity.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, String> {
}
