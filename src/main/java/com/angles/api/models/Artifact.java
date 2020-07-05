package com.angles.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Artifact {
    private String artifactId;
    private String groupId;
    private String version;
}