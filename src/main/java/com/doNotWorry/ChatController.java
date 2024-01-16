package com.doNotWorry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Value("${openai.api-key}")
    private String openaiApiKey;

    @GetMapping
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestParam(value = "question") String question, Model model) {
        try {
            String apiUrl = "https://api.openai.com/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + openaiApiKey);

            String requestData = "{ \"messages\": [ { \"role\": \"system\", \"content\": \"You are a helpful assistant.\" }, { \"role\": \"user\", \"content\": \"" + question + "\" } ] }";

            HttpEntity<String> requestEntity = new HttpEntity<>(requestData, headers);

            ResponseEntity<String> responseEntity = new RestTemplate().exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String responseBody = responseEntity.getBody();
                model.addAttribute("answer", responseBody);
            } else {
                model.addAttribute("answer", "Error: " + responseEntity.getStatusCodeValue() + ", " + responseEntity.getBody());
            }
        } catch (Exception e) {
            // 예외 처리
            model.addAttribute("answer", "Error: " + e.getMessage());
        }

        return "chat";
    }
}