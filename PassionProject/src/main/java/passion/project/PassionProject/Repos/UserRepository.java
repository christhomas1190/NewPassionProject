package passion.project.PassionProject.Repos;

import org.springframework.data.repository.CrudRepository;
import passion.project.PassionProject.Entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<Object> findByUserName(String username);
}
