package com.redcare.popularrepositories.service;

import com.redcare.popularrepositories.client.GitHubClient;
import com.redcare.popularrepositories.model.github.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {

    private final GitHubClient gitHubClient;

    public RepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<Item> getPopularRepositories(int limit, String fromDate, String language) {
        String query = "created:" + fromDate + (language != null ? " language:" + language : "");
        return gitHubClient.searchRepositories(query, "stars", "desc", limit);
    }
}
