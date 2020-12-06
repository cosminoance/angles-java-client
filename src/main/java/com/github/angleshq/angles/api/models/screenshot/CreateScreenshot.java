package com.github.angleshq.angles.api.models.screenshot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class CreateScreenshot implements Serializable {

    private String buildId;
    private String view;
    private Date timestamp;
    private String filePath;

}
