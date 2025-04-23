package passion.project.PassionProject;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;
    @Column(name="FIRSTNAME")
    private String firstName;
    @Column(name="LASTNAME")
    private String lastName;
    @Column(name="USERNAME")
    private String userName;
    @Column(name="BIRTHDAY")
    private String birthDay;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD")
    private String pw;
    @Column(name="PROFILEDESCRIPTION")
    private String profileDescription;
    //img url
    @Column(name="PROFILEPICTURE")
    private String profilePicture;
    @Column(name="USERLIST")
    private List<User> usersList = new ArrayList<>();

    @ManyToOne
    @JoinTable(
            name = "user_matches",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Matches> matchHistory = new ArrayList<>();

    public User(String firstName, String lastName, String userName,
                String birthDay, String email, String pw,
                String profileDescription, String profilePicture){
        this.firstName=firstName;
        this.lastName=lastName;
        this.userName=userName;
        this.birthDay = birthDay;
        this.email = email;
        this.pw = pw;
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
        this.pw = password;
    }

    public String getPassword() {
        return pw;
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

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public List<Matches> getMatchHistory() {return matchHistory;}

    public void setMatchHistory(List<Matches> matchHistory) {this.matchHistory = matchHistory;}

    public List<User> getUsersList() {return usersList;}

    public void setUsersList(List<User> usersList) {this.usersList = usersList;}



}
