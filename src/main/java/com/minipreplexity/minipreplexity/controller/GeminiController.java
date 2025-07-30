package com.minipreplexity.minipreplexity.controller;

import com.minipreplexity.minipreplexity.gemini.GeminiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class GeminiController {

    @Autowired
    private GeminiClient geminiClient;

    @PostMapping("/ask")
    public ResponseEntity<Map<String, String>> ask(@RequestBody Map<String, String> payload) {
        String question = payload.get("question");
        String answer = geminiClient.askGemini(question);
        return ResponseEntity.ok(Map.of("answer", answer));
    }
}
