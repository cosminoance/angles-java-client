package com.angles.api.models.screenshot;

import com.angles.api.models.BaseModel;
import com.angles.api.models.Platform;
import com.angles.api.models.build.Build;
import com.angles.api.models.build.BuildResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
