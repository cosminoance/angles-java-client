package com.angles.api.models;

import com.angles.ExecutionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter @Setter @NoArgsConstructor
public class SuiteResponse extends BaseModel {
    private String name;
    private List<String> executions = new ArrayList<>();
    private ExecutionStatus status;
    private Map<String, Integer> result;
    private Date start;

}
