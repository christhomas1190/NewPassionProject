package passion.project.PassionProject;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
public class Matches {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="MATCH_ID")
private Long id;
    @Column(name="MONTH")
    private String month;
    @Column(name="DAY")
    private String day;
    @Column(name="YEAR")
    private String year;
    @Column(name="MATCHDATE")
    private String matchDate;
    @Column(name="DATEPLAYED")
    private String datePlayed;
    @Column(name="COURSEPLAYED")
    private String coursePlayed;
    @ManyToOne
    @JoinTable(
            name = "match_players",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> players = new ArrayList<>();
    public Matches() {}
    
    public Matches(Long id, String month, String day, String year, String matchDate, String datePlayed, String coursePlayed) {
        this.id=id;
        this.month = month;
        this.day = day;
        this.year = year;
        this.matchDate = matchDate;
        this.datePlayed = datePlayed;
        this.coursePlayed = coursePlayed;
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

}
