package patterns.users.data.repository;

import org.springframework.data.repository.CrudRepository;
import patterns.users.data.entity.PassportEntity;

public interface PassportRepository extends CrudRepository<PassportEntity, String> {
}
