package com.github.angleshq.angles.api.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public abstract class BaseRequests {

    protected CloseableHttpClient client = HttpClients.createDefault();
    protected String baseUrl;
    protected Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

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

    protected CloseableHttpResponse sendMultiPartEntity(String path, Map<String, String> headers, HttpEntity entity) throws IOException {
        HttpPost httpPost = new HttpPost(baseUrl.concat(path));
        //handle headers
        for (String key: headers.keySet()) {
            httpPost.setHeader(key, headers.get(key));
        }
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(entity);

        return client.execute(httpPost);
    }

    protected CloseableHttpResponse sendJSONGet(String path) throws IOException {
        HttpGet httpGet = new HttpGet(baseUrl.concat(path));
        httpGet.setHeader("Accept", "application/json");
        return client.execute(httpGet);
    }

    protected CloseableHttpResponse sendJSONGet(String path, Map<String, Object> parameters) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(baseUrl.concat(path));
        if (parameters != null && parameters.size() > 0) {
            for (String key: parameters.keySet()) {
                builder.setParameter(key, parameters.get(key).toString());
            }
        }
        HttpGet httpGet = new HttpGet(builder.build());
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
        System.out.println(Thread.currentThread().getId() + " - PUT: " + gson.toJson(message));
        httpPut.setEntity(entity);
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        return client.execute(httpPut);
    }
}
