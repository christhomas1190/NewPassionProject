package passion.project.PassionProject.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import passion.project.PassionProject.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByBirthday(String birthDay);
    Optional<User> findByUserName(String userName);
    List<User> findByLastName(String lastName);
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
}
