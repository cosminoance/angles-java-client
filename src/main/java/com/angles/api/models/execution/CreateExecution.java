package com.angles.api.models.execution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class CreateExecution implements Serializable {

    private String build;
    private String title;
    private String suite;
    private List<String> tags = new ArrayList<>();
    private Date start;
    private List<Action> actions = new ArrayList<>();
    private List<Platform> platforms = new ArrayList<>();

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
    }
}
