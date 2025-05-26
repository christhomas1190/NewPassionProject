package passion.project.PassionProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Entity.Matches;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchesTest {
    private Matches matches;

    @BeforeEach
    public void setUp(){
        matches=new Matches("4","21","2025","4/21/2025","4/21/2025",
                "Pebble Beach");
    }

    @Test
    public void testGetters(){
        assertEquals("4", matches.getMonth());
        assertEquals("21", matches.getDay());
        assertEquals("2025", matches.getYear());
        assertEquals("4/21/2025",matches.getMatchDate());
        assertEquals("4/20/2025",matches.getDatePlayed());
        assertEquals("Pebble Beach",matches.getCoursePlayed());
    }
    @Test
     public void testSetters() {
        matches.setMonth("5");
        matches.setDay("10");
        matches.setYear("2026");
        matches.setMatchDate("05/9/2026");
        matches.setDatePlayed("05/10/2026");
        matches.setCoursePlayed("Augusta National");

        assertEquals("5", matches.getMonth());
        assertEquals("10", matches.getDay());
        assertEquals("2026", matches.getYear());
        assertEquals("05/9/2026",matches.getMatchDate());
        assertEquals("05/10/2026",matches.getDatePlayed());
        assertEquals("Augusta National",matches.getCoursePlayed());

    }
}
