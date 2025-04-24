package passion.project.PassionProject.Repos;

import org.springframework.data.repository.CrudRepository;
import passion.project.PassionProject.EndPoints.User;

public interface UserRepository extends CrudRepository<User,Long> {

}
