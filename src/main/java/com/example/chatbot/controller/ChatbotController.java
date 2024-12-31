package com.example.chatbot.controller;

import com.example.chatbot.GeminiAPI;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final GeminiAPI geminiAPI;

    public ChatbotController() {
        this.geminiAPI = new GeminiAPI();
    }

    @PostMapping("/generate")
    public String generate(@RequestBody String inputText) {
        try {
            return geminiAPI.generateText(inputText);
        } catch (Exception e) {
            return "Error generating text: " + e.getMessage();
        }
    }
}