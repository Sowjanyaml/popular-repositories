package com.redcare.popularrepositories.model.github;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String name;
    private String full_name;
    private long stargazers_count;
    private String created_at;
    private String language;
}
