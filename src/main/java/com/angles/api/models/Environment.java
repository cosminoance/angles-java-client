package com.angles.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Environment extends BaseModel {

    @Getter @Setter
    private String name;

    public Environment(String name) {
        this.name = name;
    }
}
