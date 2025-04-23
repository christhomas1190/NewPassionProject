package passion.project.PassionProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.User;

@RestController
public class UserController {

    public UserRepository userRepository;

    @Autowired
    public UserController(){
        this.userRepository=userRepository;
    }

    @GetMapping
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @PostMapping("/{id}")
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
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }
    @DeleteMapping
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }
}

