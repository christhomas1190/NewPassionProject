package passion.project.PassionProject.ControllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import passion.project.PassionProject.Controller.MatchController;
import passion.project.PassionProject.Entity.Matches;
import passion.project.PassionProject.Entity.User;
import passion.project.PassionProject.Repos.MatchesRepository;
import passion.project.PassionProject.Repos.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MatchControllerTest {
    private MatchesRepository mockMatchesRepo;
    private UserRepository mockUserRepo;
    private MatchController controller;

    @BeforeEach
    public void setUp() {
        mockMatchesRepo = mock(MatchesRepository.class);
        mockUserRepo = mock(UserRepository.class);
        controller = new MatchController(mockMatchesRepo, mockUserRepo);
    }

    @Test
    public void testGetAllMatches() {
        List<Matches> matchesList = List.of(new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Pebble Beach"));
        when(mockMatchesRepo.findAll()).thenReturn(matchesList);

        Iterable<Matches> result = controller.getAllMatches();
        assertEquals(matchesList, result);
    }

    @Test
    public void testGetMatchById_MatchExists() {
        Matches match = new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Augusta National");
        when(mockMatchesRepo.findById(1L)).thenReturn(Optional.of(match));

        Matches result = controller.getUserById(1L);
        assertEquals(match, result);
    }

    @Test
    public void testGetMatchById_NotFound() {
        when(mockMatchesRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.getUserById(1L));
    }

    @Test
    public void testUpdateMatch_MatchExists() {
        Matches existing = new Matches("04", "23", "2025", "04/23/2025", "04/23/2025", "Pebble Beach");
        Matches updated = new Matches("05", "01", "2025", "05/01/2025", "05/01/2025", "St. Andrews");

        when(mockMatchesRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(mockMatchesRepo.save(any(Matches.class))).thenReturn(existing);

        Matches result = controller.updateMatches(1L, updated);

        assertEquals("05", result.getMonth());
        assertEquals("St. Andrews", result.getCoursePlayed());
    }

    @Test
    public void testUpdateMatch_NotFound() {
        Matches updated = new Matches("05", "01", "2025", "05/01/2025", "05/01/2025", "St. Andrews");
        when(mockMatchesRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> controller.updateMatches(1L, updated));
    }

    @Test
    public void testDeleteMatch() {
        controller.deleteMatch(1L);
        verify(mockMatchesRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testLikeUser_SavesMatch() {
        User currentUser = new User();
        currentUser.setId(100L);
        currentUser.setUserName("chris");

        User likedUser = new User();
        likedUser.setId(200L);
        likedUser.setUserName("alex");

        when(mockUserRepo.findById(100L)).thenReturn(Optional.of(currentUser));
        when(mockUserRepo.findById(200L)).thenReturn(Optional.of(likedUser));

        String result = controller.likeUser(200L, 100L);

        verify(mockMatchesRepo, times(1)).save(any(Matches.class));
        assertEquals("redirect:/golfer-list", result);
    }
}
