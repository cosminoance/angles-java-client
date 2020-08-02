package com.angles.api.models.screenshot;

import com.angles.api.models.BaseModel;
import com.angles.api.models.Platform;
import com.angles.api.models.build.BuildResponse;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateScreenshotResponse extends BaseModel {

    private BuildResponse build;
    private String timestamp;
    private String thumbnail;
    private String path;
    private String view;
    private Integer height;
    private Integer width;
    private Platform platform;

}
