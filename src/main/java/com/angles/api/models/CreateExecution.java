package com.angles.api.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class CreateExecution {

    private String build;
    private String title;
    private String suite;
    private List<String> tags;
    private Date start;
    private List<Action> actions = new ArrayList<>();

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

}
