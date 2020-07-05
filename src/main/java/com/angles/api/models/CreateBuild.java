package com.angles.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
public class CreateBuild implements Serializable {
    private String team;
    private String environment;
    private String name;
    private String component;
}
