package com.angles.api.models;

import com.angles.StepStatus;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Step {

    private String name;
    private String expected;
    private String actual;
    private String info;
    private StepStatus status;
    private String screenshot;

    public Step(String name, String info, StepStatus status) {
        this.name = name;
        this.info = info;
        this.status = status;
    }

    public Step(String name, String expected, String actual, String info, StepStatus status) {
        this.name = name;
        this.expected = expected;
        this.actual = actual;
        this.info = info;
        this.status = status;
    }

    public Step(String name, String expected, String actual, String info, StepStatus status, String screenshot) {
        this.name = name;
        this.expected = expected;
        this.actual = actual;
        this.info = info;
        this.status = status;
        this.screenshot = screenshot;
    }
}
