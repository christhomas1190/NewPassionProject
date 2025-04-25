package passion.project.PassionProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.EndPoints.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/user")
public class UserController {

    public UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/list")
    public String getAllGolfers(Model model) {
        Iterable<User> allGolfers = userRepository.findAll();
        model.addAttribute("golfers", allGolfers);
        return "golfer-list";
    }
    @GetMapping("/input")
    public String showInputPage() {
        return "input";
    }

    @GetMapping("/{id}")
    public String getGolferById(@PathVariable Long id, Model model) {
        User golfer = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("golfer", golfer); //
        return "golfer-profile";
    }
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @PostMapping("/save")
    public String saveUser(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("userName") String userName,
                           @RequestParam("birthDay") String birthDay,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("profileDescription") String profileDescription,
                           @RequestParam("profilePicture") MultipartFile file) throws IOException {

        // Save uploaded profile picture
        String fileName = file.getOriginalFilename();
        Path uploadPath = Paths.get("src/main/resources/static/uploads/" + fileName);
        Files.write(uploadPath, file.getBytes());

        // Save user to database
        User newUser = new User(firstName, lastName, userName, birthDay, email, password, profileDescription, "/uploads/" + fileName);
        userRepository.save(newUser);

        return "redirect:/user/list"; // After saving, go back to show all golfers
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser){
        return userRepository.findById(id).map(user->{
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setUserName(updatedUser.getUserName());
            user.setPassword(updatedUser.getPassword());
            user.setBirthDay(updatedUser.getBirthDay());
            user.setEmail(updatedUser.getEmail());
            user.setProfileDescription(updatedUser.getProfileDescription());
            user.setProfilePicture(updatedUser.getProfilePicture());
            if (updatedUser.getProfilePicture() != null && !updatedUser.getProfilePicture().isEmpty()) {
                user.setProfilePicture(updatedUser.getProfilePicture());
            }else{
                System.out.println("Please provide a profile picture");
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }


    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}

