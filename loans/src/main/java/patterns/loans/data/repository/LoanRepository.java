package patterns.loans.data.repository;

import org.springframework.data.repository.CrudRepository;
import patterns.loans.data.entity.LoanEntity;

import java.util.List;

public interface LoanRepository extends CrudRepository<LoanEntity, String> {
    List<LoanEntity> findAllByUserId(String userId);
    List<LoanEntity> findAllByAutodebt(boolean autodebt);
}
