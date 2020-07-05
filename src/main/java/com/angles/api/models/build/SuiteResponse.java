package com.angles.api.models.build;

import com.angles.ExecutionStatus;
import com.angles.api.models.BaseModel;
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
