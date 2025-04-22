package passion.project.PassionProject.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import passion.project.PassionProject.Matches;
import passion.project.PassionProject.User;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository extends CrudRepository<Matches, Long> {

    }

