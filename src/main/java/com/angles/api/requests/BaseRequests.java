package com.angles.api.requests;

import com.google.gson.Gson;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public abstract class BaseRequests {

    protected CloseableHttpClient client = HttpClients.createDefault();
    protected String baseUrl;
    protected Gson gson = new Gson();

    protected BaseRequests(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected CloseableHttpResponse sendJSONPost(String path, Object message) throws IOException {
        HttpPost httpPost = new HttpPost(baseUrl.concat(path));
        StringEntity entity = new StringEntity(gson.toJson(message));
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        return client.execute(httpPost);
    }

    protected CloseableHttpResponse sendJSONGet(String path) throws IOException {
        HttpGet httpGet = new HttpGet(baseUrl.concat(path));
        httpGet.setHeader("Accept", "application/json");
        return client.execute(httpGet);
    }

    protected CloseableHttpResponse sendDelete(String path) throws IOException {
        HttpDelete httpDelete = new HttpDelete(baseUrl.concat(path));
        httpDelete.setHeader("Accept", "application/json");
        return client.execute(httpDelete);
    }

    protected CloseableHttpResponse sendJSONPut(String path, Object message) throws IOException {
        HttpPut httpPut = new HttpPut(baseUrl.concat(path));
        StringEntity entity = new StringEntity(gson.toJson(message));
        httpPut.setEntity(entity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        return client.execute(httpPut);
    }
}
