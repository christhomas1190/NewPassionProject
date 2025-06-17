package passion.project.PassionProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matches")
public class Matches {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="MATCH_ID")
private Long id;
    @Column(name="MATCH_MONTH")
    private String month;
    @Column(name="MATCH_DAY")
    private String day;
    @Column(name="MATCH_YEAR")
    private String year;
    @Column(name="MATCHDATE")
    private String matchDate;
    @Column(name="DATEPLAYED")
    private String datePlayed;
    @Column(name="COURSEPLAYED")
    private String coursePlayed;

    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;

    @JsonIgnore
    @ManyToMany(mappedBy = "matchHistory")
    private List<User> players;

    public Matches(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
    public Matches(String month, String day, String year, String matchDate, String datePlayed, String coursePlayed) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.matchDate = matchDate;
        this.datePlayed = datePlayed;
        this.coursePlayed = coursePlayed;
    }
    public Matches(){

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(String datePlayed) {
        this.datePlayed = datePlayed;
    }

    public String getCoursePlayed() {
        return coursePlayed;
    }

    public void setCoursePlayed(String coursePlayed) {
        this.coursePlayed = coursePlayed;
    }
    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

}
