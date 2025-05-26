package passion.project.PassionProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passion.project.PassionProject.Entity.Matches;
import passion.project.PassionProject.Repos.MatchesRepository;

@RestController
@RequestMapping("/matches")
public class MatchController {

    public MatchesRepository matchesRepository;

    @Autowired
    public  MatchController(MatchesRepository matchesRepository){
        this.matchesRepository=matchesRepository;
    }
    @GetMapping
    public Iterable<Matches> getAllMatches(){
        return matchesRepository.findAll();
    }
    @GetMapping("{id}")
    public Matches getUserById(@PathVariable Long id){
        // finds a match by id or throws an error if it doesn't exist
        return matchesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @PostMapping
    public Matches createUser(@RequestBody Matches matches) {
        //saves match in database
        return matchesRepository.save(matches);
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
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable long id) {
        matchesRepository.deleteById(id);
    }
}
