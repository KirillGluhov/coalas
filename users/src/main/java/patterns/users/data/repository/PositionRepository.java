package patterns.users.data.repository;

import org.springframework.data.repository.CrudRepository;
import patterns.users.data.entity.PositionEntity;

public interface PositionRepository extends CrudRepository<PositionEntity, String> {
}
