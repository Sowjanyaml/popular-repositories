package com.redcare.popularrepositories.client;

import com.redcare.popularrepositories.model.github.GitHubResponse;
import com.redcare.popularrepositories.model.github.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class GitHubClient {
    @Value("${api.github.service-path}")
    private String SERVICE_PATH;
    @Value("${api.github.search-api-url}")
    private String SEARCH_API_URL;
    @Value("${api.github.query}")
    private String QUERY;
    private final RestTemplate restTemplate;

    public GitHubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Item> searchRepositories(String query, String sort, String order, int limit) {

        ResponseEntity<GitHubResponse> responseEntity = restTemplate.getForEntity(SERVICE_PATH+SEARCH_API_URL+QUERY, GitHubResponse.class,query,sort,order,limit);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            GitHubResponse response = responseEntity.getBody();
            if (response != null && response.getItems() != null) {
                return response.getItems();
            }
        }
        return Collections.emptyList();
    }
}
