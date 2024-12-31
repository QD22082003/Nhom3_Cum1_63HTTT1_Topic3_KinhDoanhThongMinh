package com.example.chatbot;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class GeminiAPI {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=AIzaSyCVJ4KK5s7wdPlPETQXS9qnNhhi90lZyDE";
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateText(String inputText) throws IOException {
        // Tạo JSON đúng với cấu trúc yêu cầu
        ObjectNode jsonNode = objectMapper.createObjectNode();

        // Tạo phần nội dung
        ObjectNode contentNode = objectMapper.createObjectNode();
        ObjectNode partNode = objectMapper.createObjectNode();
        partNode.put("text", inputText);

        // Thêm phần này vào mảng "parts"
        contentNode.set("parts", objectMapper.createArrayNode().add(partNode));

        // Thêm vào "contents"
        jsonNode.set("contents", objectMapper.createArrayNode().add(contentNode));

        // Chuyển đối tượng JSON thành chuỗi
        String json = objectMapper.writeValueAsString(jsonNode);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + " Response body: " + response.body().string());
            }

            String responseBody = response.body().string();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            return rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
        }
    }
}
