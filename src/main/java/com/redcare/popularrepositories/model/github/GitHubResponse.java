package com.redcare.popularrepositories.model.github;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GitHubResponse {
    private List<Item> items;

}