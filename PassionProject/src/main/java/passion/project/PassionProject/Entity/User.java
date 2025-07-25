package passion.project.PassionProject.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //basic user info
    @Column(name="USER_ID")
    private Long id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="USER_NAME")
    private String userName;
    @Column(name="BIRTHDAY")
    private String birthDay;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PASSWORD")
    private String password;
    @Column(name="PROFILE_DESCRIPTION")
    private String profileDescription;
    @Column(name = "music_genre")
    private String musicGenres;
    @Column(name = "drinks_alcohol")
    private String drinksAlcohol;
    @Column(name = "gambles")
    private String gambles;
    @Column(name = "golf_handicap")
    private Integer handicap;
    @Column(name = "play_intensity")
    private String intensity;
    //img url
    @Column(name="PROFILE_PICTURE")
    private String profilePicture;

    public User(String firstName, String lastName, String userName, String birthDay,
                String email, String password, String profileDescription, String profilePicture,
                String musicGenres, String drinksAlcohol, String gambles, Integer handicap, String intensity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.birthDay = birthDay;
        this.email = email;
        this.password = password;
        this.profileDescription = profileDescription;
        this.profilePicture = profilePicture;
        this.musicGenres = musicGenres;
        this.drinksAlcohol = drinksAlcohol;
        this.gambles = gambles;
        this.handicap = handicap;
        this.intensity = intensity;
    }

    //list of users connected too
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_connections",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "connected_user_id")
    )
//    @OneToMany(mappedBy = "sender")
//    private List<Message> sentMessages;
//
//    @OneToMany(mappedBy = "recipient")
//    private List<Message> receivedMessages;
    private List<User> usersList = new ArrayList<>();
    //list of matches
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_matches",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Matches> matchHistory = new ArrayList<>();

//    public User(String firstName, String lastName, String userName,
//                String birthDay, String email, String password,
//                String profileDescription, String profilePicture){
//        this.firstName=firstName;
//        this.lastName=lastName;
//        this.userName=userName;
//        this.birthDay = birthDay;
//        this.email = email;
//        this.password = password;
//        this.profileDescription = profileDescription;
//        this.profilePicture = profilePicture;
//    }
    public User() {
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

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public List<Matches> getMatchHistory() {return matchHistory;}

    public void setMatchHistory(List<Matches> matchHistory) {this.matchHistory = matchHistory;}

   public List<User> getUsersList() {return usersList;}

    public void setUsersList(List<User> usersList) {this.usersList = usersList;}

    public String getMusicGenres() {
        return musicGenres;
    }
    public void setMusicGenres(String musicGenres) {
        this.musicGenres = musicGenres;
    }
    public String getDrinksAlcohol() {
        return drinksAlcohol;
    }
    public void setDrinksAlcohol(String drinksAlcohol) {
        this.drinksAlcohol = drinksAlcohol;
    }
    public String getGambles() {
        return gambles;
    }
    public void setGambles(String gambles) {
        this.gambles = gambles;
    }
    public Integer getHandicap() {
        return handicap;
    }
    public void setHandicap(Integer handicap) {
        this.handicap = handicap;
    }
    public String getIntensity() {
        return intensity;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
}
