package com.redcare.popularrepositories.controller;

import com.redcare.popularrepositories.model.github.Item;
import com.redcare.popularrepositories.service.RepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RepositoryControllerTest {

    @Mock
    private RepositoryService repositoryService;

    private RepositoryController repositoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repositoryController = new RepositoryController(repositoryService);
    }

    @Test
    void getPopularRepositories_Success() {
        // Mocking service response
        List<Item> expectedRepositories = Collections.singletonList(Item.builder().name("Sample")
                .language("Java")
                .created_at("2024-01-10")
                .stargazers_count(500)
                .full_name("SampleTest").build());
        when(repositoryService.getPopularRepositories(anyInt(), anyString(), anyString())).thenReturn(expectedRepositories);

        // Calling method under test
        ResponseEntity<List<Item>> response = repositoryController.getPopularRepositories(10, "2024-01-01", "Java");

        // Verifying behavior
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRepositories, response.getBody());
        verify(repositoryService, times(1)).getPopularRepositories(10, "2024-01-01", "Java");
    }

    @Test
    void getPopularRepositories_DefaultValues() {
        // Mocking service response
        List<Item> expectedRepositories = Collections.singletonList(Item.builder().name("Sample")
                .language("Java")
                .created_at("2023-01-01")
                .stargazers_count(500)
                .full_name("SampleTest").build());
        when(repositoryService.getPopularRepositories(10, null, null)).thenReturn(expectedRepositories);

        // Calling method under test with default parameter values
        ResponseEntity<List<Item>> response = repositoryController.getPopularRepositories(10, null, null);

        // Verifying behavior
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRepositories, response.getBody());
        assertEquals(expectedRepositories.get(0).getCreated_at(),response.getBody().get(0).getCreated_at());
        verify(repositoryService, times(1)).getPopularRepositories(10, null, null);
    }
}
