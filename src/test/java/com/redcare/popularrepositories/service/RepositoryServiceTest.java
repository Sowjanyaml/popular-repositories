package com.redcare.popularrepositories.service;

import com.redcare.popularrepositories.client.GitHubClient;
import com.redcare.popularrepositories.exception.InvalidLimitPerPage;
import com.redcare.popularrepositories.model.github.Item;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class RepositoryServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private RepositoryService repositoryService;

    public RepositoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPopularRepositories_ValidLimit() {
        // Mocking client response
        List<Item> expectedRepositories = getItems().subList(0, 2);
        when(gitHubClient.searchRepositories(anyString(), anyString(), anyString(), anyInt())).thenReturn(expectedRepositories);

        // Calling method under test
        List<Item> result = repositoryService.getPopularRepositories(2, "2023-01-01", "Java");

        // Verifying behavior
        assertEquals(expectedRepositories, result);
        verify(gitHubClient, times(1)).searchRepositories(eq("created:2023-01-01 language:Java"), eq("stars"), eq("desc"), eq(2));
    }

    @Test
    void getPopularRepositories_InvalidLimit() {
        // Verifying behavior
        assertThrows(InvalidLimitPerPage.class, () -> repositoryService.getPopularRepositories(0, "2023-01-01", "Java"));
    }

    @Test
    void getPopularRepositories_WithLanguage() {
        // Mocking client response
        List<Item> allRepositories = getItems();
        List<Item> javaRepositories = allRepositories.stream()
                .filter(item -> "Java".equals(item.getLanguage()))
                .limit(2)
                .collect(Collectors.toList());

        when(gitHubClient.searchRepositories(anyString(), anyString(), anyString(), anyInt())).thenReturn(javaRepositories);

        // Calling method under test
        List<Item> result = repositoryService.getPopularRepositories(10, "2023-01-01", "Java");

        // Verifying behavior
        assertEquals(javaRepositories, result);
        assertEquals(2, result.size());
        verify(gitHubClient, times(1)).searchRepositories(eq("created:2023-01-01 language:Java"), eq("stars"), eq("desc"), eq(10));
    }

    @Test
    void getPopularRepositories_NoLanguage() {
        // Mocking client response
        List<Item> allRepositories = getItems();
        List<Item> repositoriesWithoutLanguage = allRepositories.stream()
                .filter(item -> item.getLanguage() == null)
                .limit(10)
                .collect(Collectors.toList());

        when(gitHubClient.searchRepositories(anyString(), anyString(), anyString(), anyInt())).thenReturn(repositoriesWithoutLanguage);

        // Calling method under test
        List<Item> result = repositoryService.getPopularRepositories(10, "2023-01-01", null);

        // Verifying behavior
        assertEquals(repositoriesWithoutLanguage, result);
        assertEquals(1, result.size());
        verify(gitHubClient, times(1)).searchRepositories(eq("created:2023-01-01"), eq("stars"), eq("desc"), eq(10));
    }


    private static List<Item> getItems() {
        return List.of(Item.builder().name("Sample")
                        .language("Java")
                        .created_at("2023-01-01")
                        .stargazers_count(500)
                        .full_name("SampleTest1").build(),
                Item.builder().name("Sample1")
                        .language("Go")
                        .created_at("2023-10-01")
                        .stargazers_count(400)
                        .full_name("SampleTest2").build(),
                Item.builder().name("Sample")
                        .language("Java")
                        .created_at("2023-12-01")
                        .stargazers_count(243)
                        .full_name("SampleTest3").build(),
                Item.builder().name("Sample")
                        .language(null)
                        .created_at("2023-12-01")
                        .stargazers_count(243)
                        .full_name("SampleTest3").build());
    }
}
