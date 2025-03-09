package patterns.core.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patterns.core.data.entity.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    List<AccountEntity> findAllByUserId(String userId);
}
