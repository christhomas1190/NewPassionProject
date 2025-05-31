package passion.project.PassionProject.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Controller.UserController;
import passion.project.PassionProject.Entity.User;
import passion.project.PassionProject.Repos.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTests {

    private UserController controller;
    private InMemoryUserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new InMemoryUserRepository() {
            @Override
            public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public Iterable<User> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }
        };
        controller = new UserController(userRepository);
    }

    @Test
    public void testGetAllUsers() {
        User user = new User("Tiger", "Woods", "tiger.woods", "12/30/1975", "tiger@golf.com", "pass", "desc", "pic", "", "", "", null, "");
        userRepository.save(user);
        Iterable<User> users = controller.getAllUsers();
        assertTrue(users.iterator().hasNext());
    }

    @Test
    public void testUpdateUser_UserExists() {
        User original = new User("Tiger", "Woods", "tiger", "12/30/1975", "tiger@a.com", "pass", "desc", "pic", "", "", "", null, "");
        original.setId(1L);
        userRepository.save(original);

        User updated = new User("Tiger", "Woods", "goat", "12/30/1975", "goat@a.com", "newpass", "new desc", "newpic", "", "", "", null, "");

        User result = controller.updateUser(1L, updated);

        assertEquals("goat", result.getUserName());
        assertEquals("goat@a.com", result.getEmail());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        User updated = new User("Tiger", "Woods", "goat", "12/30/1975", "goat@a.com", "newpass", "new desc", "newpic", "", "", "", null, "");
        Exception exception = assertThrows(RuntimeException.class, () -> controller.updateUser(99L, updated));
        assertTrue(exception.getMessage().contains("User not found"));
    }

//    @Test
//    public void testDeleteUser() {
//        InMemoryUserRepository userRepository = new InMemoryUserRepository();
//        UserController controller = new UserController(userRepository); // use the same repo
//
//        User user = new User("Tiger", "Woods", "tiger", "12/30/1975", "tiger@a.com", "pass", "desc", "pic", "", "", "", null, "");
//        user = userRepository.save(user); // assign ID internally
//
//        System.out.println("Before delete - User ID: " + user.getId());
//
//        controller.deleteUser(user.getId());
//
//        boolean stillExists = userRepository.findById(user.getId()).isPresent();
//        System.out.println("After delete - User still exists: " + stillExists);
//
//        assertFalse(stillExists);
//    }

    // Simple in-memory UserRepository implementation
    static abstract class InMemoryUserRepository implements UserRepository {
        private final List<User> users = new ArrayList<>();
        private long idCounter = 1;

        @Override
        public <S extends User> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idCounter++);
            } else {
                deleteById(entity.getId());
            }
            users.add(entity);
            return entity;
        }

        @Override
        public List<User> findAll() {
            return users;
        }

        @Override
        public Optional<User> findById(Long id) {
            return users.stream().filter(u -> u.getId().equals(id)).findFirst();
        }
    }
}
