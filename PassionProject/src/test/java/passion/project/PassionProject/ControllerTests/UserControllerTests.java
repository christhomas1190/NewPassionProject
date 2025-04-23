package passion.project.PassionProject.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Controller.UserController;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTests {
    private UserRepository mockRepo;
    private UserController controller;

//    @BeforeEach
//    public void setUp() {
//        mockRepo = mock(UserRepository.class);         // Create a fake UserRepository
//        controller = new UserController();              // Create the controller
//        controller.userRepository = mockRepo;           // Inject the mock repository
//    }

//    @Test
//    public void testGetAllUsers() {
//        List<User> mockUsers = Arrays.asList(
//                new User("John", "Doe", "jdoe", "01-01-2000", "jdoe@email.com", "pass123", "bio", "pic"),
//                new User("Jane", "Smith", "jsmith", "02-02-1995", "jsmith@email.com", "pass456", "bio2", "pic2")
//        );
//
//        when(mockRepo.findAll()).thenReturn(mockUsers);
//
//        Collections<User> result = controller.getAllUsers();
//        assertEquals(2, Collections.s);
//        assertEquals("jdoe", result.get(0).getUserName());
//    }

    @Test
    public void testGetUserById_Found() {
        User user = new User("John", "Doe", "jdoe", "01-01-2000", "jdoe@email.com", "pass123", "bio", "pic");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        User result = controller.getUserById(1L);
        assertEquals("jdoe", result.getUserName());
    }

    @Test
    public void testGetUserById_NotFound() {
        when(mockRepo.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            controller.getUserById(99L);
        });

        assertEquals("User not found", exception.getMessage());
    }
}
