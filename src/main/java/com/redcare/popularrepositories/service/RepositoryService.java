package com.redcare.popularrepositories.service;

import com.redcare.popularrepositories.client.GitHubClient;
import com.redcare.popularrepositories.model.github.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.redcare.popularrepositories.util.Validator.validate;

@Service
public class RepositoryService {

    @Value("${api.github.sort-by}")
    private String SORT_BY;
    @Value("${api.github.order-by}")
    private String ORDER_BY;
    @Value("${api.github.date-parameter}")
    private String DATE_PARAMETER;
    @Value("${api.github.language}")
    private String LANGUAGE;
    private final String COLON= ":";
    private final String SPACE= " ";
    private final GitHubClient gitHubClient;

    public RepositoryService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<Item> getPopularRepositories(int limit, String fromDate, String language) {
        validate(limit, fromDate);
        String query = DATE_PARAMETER + COLON + fromDate +(language != null ? SPACE + LANGUAGE + COLON + language : "");
        return gitHubClient.searchRepositories(query, SORT_BY, ORDER_BY, limit);
    }


}
