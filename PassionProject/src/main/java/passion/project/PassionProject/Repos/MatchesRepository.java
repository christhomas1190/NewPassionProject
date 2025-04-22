package passion.project.PassionProject.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import passion.project.PassionProject.Matches;
import passion.project.PassionProject.User;

import java.util.List;
import java.util.Optional;

public interface MatchesRepository extends JpaRepository{
    List<Matches> findByCoursePlayed(String coursePlayed);
    List<Matches> findByYear(String year);
    List<Matches> findByDatePlayed(String datePlayed);
    }

