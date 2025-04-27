package passion.project.PassionProject.imgTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class imageTests {
    private TestRestTemplate restTemplate;
    private String imageUrl;

    @BeforeEach
    public void setUp() {
        // Given
        restTemplate = new TestRestTemplate();
        imageUrl = "http://localhost:8080/uploads/rory.jpg"; // Replace with your actual uploaded image path
    }

    @Test
    public void givenProfilePicture_whenRequested_thenImageIsAccessible() {
        // When
        ResponseEntity<byte[]> response = restTemplate.getForEntity(imageUrl, byte[].class);

        // Then
        assertThat(response.getStatusCode().is2xxSuccessful())
                .as("Profile picture should be accessible at " + imageUrl)
                .isTrue();
    }
}
