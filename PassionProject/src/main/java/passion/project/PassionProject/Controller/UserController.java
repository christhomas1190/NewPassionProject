package passion.project.PassionProject.Controller;

import jakarta.servlet.http.HttpSession;
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
import java.util.List;
import java.util.Optional;

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
        User newUser = new User(
                firstName,
                lastName,
                userName,
                birthDay,
                email,
                password,
                profileDescription,
                profilePicturePath,
                "", "", "", null, "" // empty/default preference values for now
        );

        User savedUser = userRepository.save(newUser);// Save the user into database

        // Redirect back to golfer pfp upload page
        return "redirect:/user/pfp?userId=" +savedUser.getId();
    }

    @PostMapping("/pfp")
    public String uploadProfilePicture(@RequestParam("profilePicture") MultipartFile file,
                                       @RequestParam("userId") Long userId,
                                       HttpSession session) throws IOException {

        System.out.println("Upload attempt: file name = " + file.getOriginalFilename() + ", size = " + file.getSize());

        if (file.isEmpty()) {
            System.out.println("Upload failed: file was empty.");
            return "redirect:/user/pfp?userId=" + userId;
        }

        Path uploadPath = Paths.get("WebUploads");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            System.out.println("Created upload directory at: " + uploadPath.toAbsolutePath());
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        System.out.println("Uploaded file saved to: " + filePath.toAbsolutePath());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found when uploading picture"));

        user.setProfilePicture("/WebUploads/" + fileName);
        userRepository.save(user);

        System.out.println("User " + user.getUserName() + " profile picture updated to: /WebUploads/" + fileName);

        session.setAttribute("userId", userId);

        return "redirect:/user/preferences";
    }
    @PostMapping("user/preferences")
    public String savePreferences(
            @RequestParam List<String> musicGenres,
            @RequestParam String drinksAlcohol,
            @RequestParam String gambles,
            @RequestParam String intensity,
            @RequestParam double handicap,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        User user = userRepository.findById(userId).orElseThrow();

        user.setMusicGenre(String.join(", ", musicGenres)); // or however your field is structured
        user.setDrinksAlcohol(drinksAlcohol);
        user.setGambles(gambles);
        user.setIntensity(intensity);
        user.setHandicap((int) handicap);

        userRepository.save(user);

        return "redirect:/user/profile";
    }


    @GetMapping("/pfp")
    public String showPfpPage(@RequestParam("userId")Long userId,Model model){
        model.addAttribute("userId",userId);// add the userId to the model
        return "pfp";
    }
    @GetMapping("/preferences")
    public String showPreferencesForm() {
        return "preferences";
    }
    @PostMapping("/preferences")
    public String savePreferences(
            @RequestParam("musicGenre") String musicGenre,
            @RequestParam("drinksAlcohol") String drinksAlcohol,
            @RequestParam("gambles") String gambles,
            @RequestParam("handicap") Integer handicap,
            @RequestParam("intensity") String intensity,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Optional<User> optionalUser =userRepository.findById(userId);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.setMusicGenre(musicGenre);
            user.setDrinksAlcohol(drinksAlcohol);
            user.setGambles(gambles);
            user.setHandicap(handicap);
            user.setIntensity(intensity);
            userRepository.save(user);
        }
        return "redirect:/user/" + userId;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}

