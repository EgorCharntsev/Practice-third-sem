package ru.kpfu.itis.charntsev.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class NetSample implements HttpClient{

    private String readContent(HttpURLConnection connection) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
            return content.toString();
        }
    }

    //get
    @Override
    public String get(String url, Map<String, String> params) throws IOException {
        URL getUrl;
        if (params == null || params.isEmpty()) getUrl = new URL(url);
        else {
            int count = 0;
            StringBuilder strUrl = new StringBuilder(url + "?");
            while (params.keySet().toArray().length > count) {
                String key = params.keySet().toArray()[count].toString();
                strUrl.append(key).append("=").append(params.get(key)).append("&");
                count++;
            }
            getUrl = new URL(strUrl.substring(0,strUrl.length() - 1));
        }
        HttpURLConnection getConnection = (HttpURLConnection) getUrl.openConnection();

        getConnection.setRequestMethod("GET");
        getConnection.setRequestProperty("Content-Type", "application/json");

        return readContent(getConnection);
    }

    //post
    @Override
    public String post(String url, Map<String, String> params) throws IOException {
        URL postUrl = new URL(url);
        HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();

        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setRequestProperty("Accept", "application/json");
        postConnection.setRequestProperty("Authorization", "Bearer 86961842d5f233bff4c46eab333ec9bd43706e95c9758b642a0ef403e2315aa6");

        postConnection.setDoOutput(true);

        StringBuilder jsonInput = new StringBuilder("{");
        int count = 0;
        while (params.keySet().toArray().length > count) {
            String key = params.keySet().toArray()[count].toString();
            jsonInput.append("\"").append(key).append("\":\"").append(params.get(key)).append("\", ");
            count++;
        }
        jsonInput = new StringBuilder(jsonInput.substring(0, jsonInput.length() - 2) + "}");

        try(OutputStream outputStream = postConnection.getOutputStream()) {
            byte [] input = jsonInput.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input,0,input.length);
        }

        return readContent(postConnection);
    }

    //put
    @Override
    public String put(String url, Map<String, String> params) throws IOException {
        URL putUrl = new URL(url);
        HttpURLConnection putConnection = (HttpURLConnection) putUrl.openConnection();

        putConnection.setRequestMethod("PUT");
        putConnection.setRequestProperty("Content-Type", "application/json");
        putConnection.setRequestProperty("Accept", "application/json");
        putConnection.setRequestProperty("Authorization", "Bearer 86961842d5f233bff4c46eab333ec9bd43706e95c9758b642a0ef403e2315aa6");

        putConnection.setDoOutput(true);

        StringBuilder jsonInput = new StringBuilder("{");
        int count = 0;
        while (params.keySet().toArray().length > count) {
            String key = params.keySet().toArray()[count].toString();
            jsonInput.append("\"").append(key).append("\":\"").append(params.get(key)).append("\", ");
            count++;
        }
        jsonInput = new StringBuilder(jsonInput.substring(0, jsonInput.length() - 2) + "}");

        try(OutputStream outputStream = putConnection.getOutputStream()) {
            byte [] input = jsonInput.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input,0,input.length);
        }

        return readContent(putConnection);
    }

    //delete
    @Override
    public String delete(String url, Map<String, String> params) throws IOException {
        URL deleteUrl = new URL(url);
        HttpURLConnection deleteConnection = (HttpURLConnection) deleteUrl.openConnection();

        deleteConnection.setRequestMethod("DELETE");
        deleteConnection.setRequestProperty("Content-Type", "application/json");
        deleteConnection.setRequestProperty("Accept", "application/json");
        deleteConnection.setRequestProperty("Authorization", "Bearer 86961842d5f233bff4c46eab333ec9bd43706e95c9758b642a0ef403e2315aa6");

        deleteConnection.setDoOutput(true);

        return readContent(deleteConnection);
    }
}
