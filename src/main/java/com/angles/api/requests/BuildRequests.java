package com.angles.api.requests;

import com.angles.api.models.Build;
import com.angles.api.models.CreateBuild;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class BuildRequests extends BaseRequests {

    private String basePath = "build";
    public BuildRequests(String baseUrl) {
        super(baseUrl);
    }

    public Build create(CreateBuild createBuild) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, createBuild);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        }
        return null;
    }

}
