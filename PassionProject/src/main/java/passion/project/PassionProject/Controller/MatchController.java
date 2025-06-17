package passion.project.PassionProject.Controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passion.project.PassionProject.Entity.Matches;
import passion.project.PassionProject.Entity.User;
import passion.project.PassionProject.Repos.MatchesRepository;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.UserNotFoundException;

import java.security.Principal;

@RestController
@RequestMapping("/matches")
public class MatchController {

    public MatchesRepository matchesRepository;
    public UserRepository userRepository;

    @Autowired
    public MatchController(MatchesRepository matchesRepository, UserRepository userRepository) {
        this.matchesRepository = matchesRepository;
        this.userRepository = userRepository;
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
    @GetMapping("/match/view/{id}")
    public String viewGolferById(@PathVariable Long id, Model model) {
        User golfer = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Golfer not found"));

        model.addAttribute("golfer", golfer);
        return "match-view";
    }
    @PostMapping("/match/like/{id}")
    public String likeUser(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        User currentUser = userRepository.getUserName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        matchesRepository.save(new Matches(currentUser, likedUser));
        return "redirect:/golfer-list";
    }
}
