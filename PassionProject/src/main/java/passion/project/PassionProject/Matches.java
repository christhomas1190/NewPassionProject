package passion.project.PassionProject;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(name = "matches")
public class Matches {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
    private int month;
    private int day;
    private String year;
    private String matchDate;
    private String datePlayed;
    private String coursePlayed;

    public Matches() {
    }
    
    public Matches(int month, int day, String year, String matchDate, String datePlayed, String coursePlayed) {
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
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
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
