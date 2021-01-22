package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.models.Platform;
import com.github.angleshq.angles.api.models.screenshot.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ScreenshotRequests extends BaseRequests {

    private String basePath = "screenshot";
    private Tika tika = new Tika();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public ScreenshotRequests(String baseUrl) {
        super(baseUrl);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public Screenshot create(CreateScreenshot createScreenshot) throws IOException {
        File screenShotFile = new File(createScreenshot.getFilePath());
        String mimeType = tika.detect(screenShotFile);
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .addBinaryBody("screenshot", screenShotFile, ContentType.getByMimeType(mimeType), screenShotFile.getName())
                .build();

        Map<String, String> headers = new HashMap<>();
        headers.put("view", createScreenshot.getView());
        headers.put("buildId", createScreenshot.getBuildId());
        headers.put("timestamp", sdf.format(createScreenshot.getTimestamp()));

        CloseableHttpResponse response = sendMultiPartEntity(basePath, headers, entity);
        String responseBody = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            return gson.fromJson(responseBody, Screenshot.class);
        } else {
            System.out.println(response.getStatusLine().getStatusCode() + ": " + responseBody);
        }
        return null;
    }

    public Screenshot[] get(String buildId, String view, Integer limit, Integer skip) throws IOException, URISyntaxException {
        Map<String, Object> parameters = new HashMap<>();
        if (buildId != null) { parameters.put("buildId", view); }
        if (view != null) { parameters.put("view", view); }
        if (limit != null) { parameters.put("limit", limit); }
        if (skip != null) { parameters.put("skip", skip); }
        CloseableHttpResponse response = sendJSONGet(basePath, parameters);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Screenshot[].class);
        }
        return null;
    }

    public Screenshot get(String screenshotId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + screenshotId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Screenshot.class);
        }
        return null;
    }

    public ImageCompareResponse baselineCompare(String screenshotId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + screenshotId + "/baseline/compare");
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, ImageCompareResponse.class);
        }
        return null;
    }

    public Boolean delete(String screenshotId) throws IOException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + screenshotId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }

    public Screenshot update(String screenshotId, UpdateScreenshot updateRequest) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + screenshotId, updateRequest);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Screenshot.class);
        }
        return null;
    }
}
