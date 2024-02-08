package com.redcare.popularrepositories.client;

import com.redcare.popularrepositories.model.github.GitHubResponse;
import com.redcare.popularrepositories.model.github.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GitHubClientTest {
    @Mock
    private RestTemplate restTemplate;

    private GitHubClient gitHubClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gitHubClient = new GitHubClient(restTemplate);
    }

    @Test
    void searchRepositories_Success() {

        GitHubResponse gitHubResponse = new GitHubResponse();
        gitHubResponse.setItems(Collections.singletonList(Item.builder().name("Sample")
                .language("Java")
                .created_at("2024-01-01")
                .stargazers_count(500)
                .full_name("SampleTest").build()));
        ResponseEntity<GitHubResponse> responseEntity = new ResponseEntity<>(gitHubResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(GitHubResponse.class), any(), any(), any(), anyInt()))
                .thenReturn(responseEntity);

        List<Item> result = gitHubClient.searchRepositories("query", "sort", "order", 10);

        assertEquals(1, result.size());

        verify(restTemplate, times(1))
                .getForEntity(anyString(), eq(GitHubResponse.class), any(), any(), any(), anyInt());
    }

    @Test
    void searchRepositories_EmptyResponse() {

        GitHubResponse gitHubResponse = new GitHubResponse();
        ResponseEntity<GitHubResponse> responseEntity = new ResponseEntity<>(gitHubResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(GitHubResponse.class), any(), any(), any(), anyInt()))
                .thenReturn(responseEntity);

        List<Item> result = gitHubClient.searchRepositories("query", "sort", "order", 10);

        assertEquals(Collections.emptyList(), result);
        verify(restTemplate, times(1))
                .getForEntity(anyString(), eq(GitHubResponse.class), any(), any(), any(), anyInt());
    }
}
