package patterns.loans.data.repository;

import org.springframework.data.repository.CrudRepository;
import patterns.loans.data.entity.TariffEntity;

public interface TariffRepository extends CrudRepository<TariffEntity, String> {
}
