package com.github.angleshq.angles.api.models.screenshot;

import com.github.angleshq.angles.api.models.BaseModel;
import com.github.angleshq.angles.api.models.Platform;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Screenshot extends BaseModel {

    private String build;
    private String timestamp;
    private String thumbnail;
    private String path;
    private String view;
    private Integer height;
    private Integer width;
    private Platform platform;

}
