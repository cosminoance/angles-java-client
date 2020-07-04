package com.angles.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class CreateBuild {
    private String team;
    private String environment;
    private String name;
    private String component;
}
