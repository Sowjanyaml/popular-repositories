package com.redcare.popularrepositories.service;

import com.redcare.popularrepositories.client.GitHubClient;
import com.redcare.popularrepositories.exception.InvalidLimitPerPage;
import com.redcare.popularrepositories.model.github.Item;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.redcare.popularrepositories.util.DateValidator.dateValidation;

@Service
public class RepositoryService {

    private final GitHubClient gitHubClient;

    public RepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<Item> getPopularRepositories(int limit, String fromDate, String language) {
        if (limit <= 0) {
            throw new InvalidLimitPerPage("The value " + limit + " is not a valid page limit. Please enter a valid limit per page.");
        }
        dateValidation(fromDate);
        String query = "created:" + fromDate + (language != null ? " language:" + language : "");
        return gitHubClient.searchRepositories(query, "stars", "desc", limit);
    }

}
