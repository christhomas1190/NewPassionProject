package passion.project.PassionProject.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Controller.MatchController;
import passion.project.PassionProject.EndPoints.Matches;
import passion.project.PassionProject.Repos.MatchesRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MatchControllerTest {
    private MatchesRepository mockRepo;
    private MatchController controller;

    @BeforeEach
    public void setUp() {
        mockRepo = mock(MatchesRepository.class);
        controller = new MatchController(mockRepo);
    }

    @Test
    public void testGetAllMatches() {
        List<Matches> matchesList = List.of(new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Pebble Beach"));
        when(mockRepo.findAll()).thenReturn(matchesList);

        Iterable<Matches> result = controller.getAllMatches();
        assertEquals(matchesList, result);
    }

    @Test
    public void testGetMatchById_MatchExists() {
        Matches match = new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Augusta National");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(match));

        Matches result = controller.getUserById(1L);
        assertEquals(match, result);
    }

    @Test
    public void testGetMatchById_NotFound() {
        when(mockRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.getUserById(1L));
    }

    @Test
    public void testUpdateMatch_MatchExists() {
        Matches existing = new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Pebble Beach");
        Matches updated = new Matches("05", "01", "2025", "05/01/2025", "05/01/2025", "St. Andrews");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(mockRepo.save(any(Matches.class))).thenReturn(existing);

        Matches result = controller.updateMatches(1L, updated);

        assertEquals("05", result.getMonth());
        assertEquals("St. Andrews", result.getCoursePlayed());
    }

    @Test
    public void testUpdateMatch_NotFound() {
        Matches updated = new Matches("05", "01", "2025", "05/01/2025", "05/01/2025", "St. Andrews");
        when(mockRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.updateMatches(1L, updated));
    }

    @Test
    public void testDeleteMatch() {
        controller.deleteMatch(1L);
        verify(mockRepo, times(1)).deleteById(1L);
    }
}
