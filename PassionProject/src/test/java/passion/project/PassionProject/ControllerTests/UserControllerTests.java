package passion.project.PassionProject.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Controller.UserController;
import passion.project.PassionProject.Repos.UserRepository;
import passion.project.PassionProject.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTests {
    private UserRepository mockRepo;
    private UserController controller;

    @BeforeEach
    public void setUp() {
        mockRepo = mock(UserRepository.class);
        controller = new UserController(mockRepo);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = List.of(new User("Tiger", "Woods", "tiger.woods", "12/30/1975", "tiger@golf.com", "pass", "desc", "pic"));
        when(mockRepo.findAll()).thenReturn(users);

        Iterable<User> result = controller.getAllUsers();
        assertEquals(users, result);
    }

    @Test
    public void testGetUserById_UserExists() {
        User user = new User("Tiger", "Woods", "tiger.woods", "12/30/1975", "tiger@golf.com", "pass", "desc", "pic");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        User result = controller.getUserById(1L);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(mockRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.getUserById(1L));
    }

    @Test
    public void testUpdateUser_UserExists() {
        User existing = new User("Tiger", "Woods", "tiger.woods", "12/30/1975", "tiger@golf.com", "pass", "desc", "pic");
        User updated = new User("Tiger", "Woods", "tiger.thegoat", "12/30/1975", "tiger@goat.com", "newpass", "new desc", "new pic");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(mockRepo.save(any(User.class))).thenReturn(existing);

        User result = controller.updateUser(1L, updated);

        assertEquals("tiger.thegoat", result.getUserName());
        assertEquals("tiger@goat.com", result.getEmail());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        User updated = new User("Tiger", "Woods", "tiger.thegoat", "12/30/1975", "tiger@goat.com", "newpass", "new desc", "new pic");
        when(mockRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.updateUser(1L, updated));
    }

    @Test
    public void testDeleteUser() {
        controller.deleteUser(1L);
        verify(mockRepo, times(1)).deleteById(1L);
    }
}

