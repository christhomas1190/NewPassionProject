package passion.project.PassionProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.Entity.User;

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
    //this is what was returning the json to the webpage
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }


    @GetMapping("/list")
    public String getAllGolfers(Model model) {
        Iterable<User> allGolfers = userRepository.findAll();
        // DEBUGGING
        // — this will print every golfer and their picture path to the console
        for (User u : allGolfers) {
            System.out.println("User: " + u.getFirstName() + ", Profile Picture: " + u.getProfilePicture());
        }
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

            // check if profile picture is missing
            if (updatedUser.getProfilePicture() != null && !updatedUser.getProfilePicture().isEmpty()) {
                user.setProfilePicture(updatedUser.getProfilePicture());
            }else{
                System.out.println("Please provide a profile picture");
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    @PostMapping("/save")
    //@requestbody asks for json
    //requestparams is looking for specific field from html
    public String saveUser(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName,
                           @RequestParam("userName") String userName,
                           @RequestParam("birthDay") String birthDay,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("profileDescription") String profileDescription,
                           @RequestParam(value = "profilePicture", required = false) String profilePicturePath){


        // Create new user with profile picture path (null if no picture uploaded)
        User newUser = new User(firstName, lastName, userName, birthDay, email, password, profileDescription, profilePicturePath);
        User savedUser = userRepository.save(newUser);// Save the user into database
        userRepository.save(newUser);

        // Redirect back to golfer pfp upload page
        return "redirect:/user/pfp?userId=" +savedUser.getId();
    }

    @PostMapping("/pfp")
    public String uploadProfilePicture(@RequestParam("profilePicture") MultipartFile file,
                                       @RequestParam("userId") Long userId) throws IOException {
        if (file.isEmpty()) {
            return "redirect:/user/pfp"; // If no file, stay on upload page
        }

        // Save the file
        Path uploadPath = Paths.get("WebUploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        // Update the user with profile picture path
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found when uploading picture"));

        user.setProfilePicture("/WebUploads/" + fileName);
        userRepository.save(user);

        return "redirect:/user/list"; // Now go to golfer list
    }
    @GetMapping("/pfp")
    public String showPfpPage(@RequestParam("userId")Long userId,Model model){
        model.addAttribute("userId",userId);// add the userId to the model
        return "pfp";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}

