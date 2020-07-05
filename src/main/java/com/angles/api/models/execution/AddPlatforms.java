package com.angles.api.models.execution;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AddPlatforms {

    private List<Platform> platforms = new ArrayList<>();

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
    }

}
