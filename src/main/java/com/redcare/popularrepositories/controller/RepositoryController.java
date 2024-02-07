package com.redcare.popularrepositories.controller;

import com.redcare.popularrepositories.model.github.Item;
import com.redcare.popularrepositories.service.RepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// Controller Class
@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService gitHubService;

    public RepositoryController(RepositoryService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Item>> getPopularRepositories(
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "2023-01-01") String fromDate,
            @RequestParam(required = false) String language) {

        List<Item> popularRepositories = gitHubService.getPopularRepositories(limit, fromDate, language);
        return ResponseEntity.ok(popularRepositories);
    }
}

