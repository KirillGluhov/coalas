package patterns.users.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patterns.users.data.entity.UserEntity;
import patterns.users.data.enums.Role;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
    List<UserEntity> findByRole(Role role);
    UserEntity findByPhone(String phone);
    UserEntity findByEmail(String email);
}
