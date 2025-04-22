package passion.project.PassionProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Christian", "Thomas","christhomas1190", "11/11/1990",
                "cthomas@email.com", "password", "This is a description I would have on my profile.", ":)");
    }

    @Test
    public void usersGettersTest(){
        assertEquals("Christian", user.getFirstName());
        assertEquals("Thomas", user.getLastName());
        assertEquals("christhomas1190", user.getUserName());
        assertEquals("cthomas@email.com", user.getEmail());
        assertEquals("11/11/1990", user.getBirthDay());
        assertEquals("password", user.getPassword());
        assertEquals("This is a description I would have on my profile.",
                user.getProfileDescription());
        assertEquals(":)", user.getProfilePicture());

    }
    @Test
    public void usersSetterTests(){
        user.setFirstName("Christian");
        user.setLastName("Thomas");
        user.setBirthDay("11/11/1990");
        user.setUserName("cthomas1190");
        user.setPassword("password");
        user.setProfileDescription("This is a description I would have on my profile.");
        user.setProfilePicture(":)");


        assertEquals("Christian", user.getFirstName());
        assertEquals("Thomas", user.getLastName());
        assertEquals("11/11/1990", user.getBirthDay());
        assertEquals("cthomas1190", user.getUserName());
        assertEquals("password", user.getPassword());
        assertEquals("This is a description I would have on my profile.", user.getProfileDescription());
        assertEquals(":)", user.getProfilePicture());
    }
}
