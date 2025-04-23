package passion.project.PassionProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passion.project.PassionProject.Matches;
import passion.project.PassionProject.Repos.MatchesRepository;
import passion.project.PassionProject.User;

@RestController
@RequestMapping("/matches")
public class MatchController {

    public MatchesRepository matchesRepository;

    @Autowired
    public  MatchController(MatchesRepository matchesRepository){
        this.matchesRepository=matchesRepository;
    }
    @GetMapping
    public Iterable<Matches> getAllUsers(){
        return matchesRepository.findAll();
    }
    @GetMapping("{id}")
    public Matches getUserById(@PathVariable Long id){
        return matchesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @PostMapping("/{id}")
    public Matches updateMatches(@PathVariable Long id, @RequestBody Matches updatedMatches){
        return matchesRepository.findById(id).map(matches->{
            matches.setId(updatedMatches.getId());
            matches.setMatchDate(updatedMatches.getMatchDate());
            matches.setCoursePlayed(updatedMatches.getCoursePlayed());
            matches.setDay(updatedMatches.getDay());
            matches.setMonth(updatedMatches.getMonth());
            matches.setYear(updatedMatches.getYear());
            matches.setDatePlayed(updatedMatches.getDatePlayed());
            return matchesRepository.save(matches);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
}
