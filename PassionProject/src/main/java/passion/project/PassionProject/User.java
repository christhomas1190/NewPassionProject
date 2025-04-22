package passion.project.PassionProject;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;


@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String birthDay;
    private String email;
    private String password;
    private String profileDescription;
    //img url
    private String profilePicture;

    public User(String firstName, String lastName, String userName,
                String birthDay, String email, String password,
                String profileDescription, String profilePicture){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.birthDay = birthDay;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.profilePicture = profilePicture;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }



}
