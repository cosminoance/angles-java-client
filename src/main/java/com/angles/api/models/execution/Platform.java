package com.angles.api.models.execution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class Platform {

    private String platformName;
    private String platformVersion;
    private String browserName;
    private String browserVersion;
    private String deviceName;
    private String deviceBrand;
    private String deviceModel;
    private String userAgent;
    private Integer screenHeight;
    private Integer screenWidth;
    private Float pixelRatio;

    public Platform(String platformName, String platformVersion, String browserName, String browserVersion) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    public Platform(String platformName, String platformVersion, String browserName, String browserVersion, String deviceName, String deviceBrand, String deviceModel) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
    }

    public Platform(String platformName, String platformVersion, String browserName, String browserVersion, String deviceName, String deviceBrand, String deviceModel, String userAgent, Integer screenHeight, Integer screenWidth, Float pixelRatio) {
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.deviceName = deviceName;
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
        this.userAgent = userAgent;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.pixelRatio = pixelRatio;
    }
}
