package patterns.loans.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import patterns.loans.data.document.OperationDocument;

import java.util.List;

@Repository
public interface OperationRepository extends MongoRepository<OperationDocument, String> {
    List<OperationDocument> findByLoanId(String loanId);
}

