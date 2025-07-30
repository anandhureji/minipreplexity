package com.minipreplexity.minipreplexity.gemini;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GeminiClient {


    @Value("${google.gemini.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String askGemini(String question){
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
        Map<String,Object> request = Map.of("contents", List.of(
                Map.of("parts",List.of(Map.of("text",question)))
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String,Object>> entity = new HttpEntity<>(request,headers);
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url,entity,Map.class);
            Map result = response.getBody();
            List<Map> candidates = (List<Map>) result.get("candidates");
            Map content = (Map) candidates.get(0).get("content");
            List<Map> parts = (List<Map>) content.get("parts");
            return (String) parts.get(0).get("text");

        } catch (RestClientException e) {
            return "Error:"+e.getMessage();
        }


    }
}
