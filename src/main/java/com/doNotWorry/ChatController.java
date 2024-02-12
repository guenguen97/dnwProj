package com.doNotWorry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/chat")
public class ChatController {




    @Value("${chatgpt.api-key}")
    private String openaiApiKey;
    @GetMapping
    public String chatPage() {
        return "chat";
    }

    @PostMapping("/ask")
    @ResponseBody
    public String askQuestion(@RequestParam(value = "question") String question, Model model) throws IOException {
        System.out.println("질문 받기 성공 !!!!!!!!!!!!!!!!");
        System.out.println(openaiApiKey+"!!!!!!!!!!!!!!!!!!");
        ResponseEntity<String> responseEntity = null;
        try {
            String apiUrl = "https://api.openai.com/v1/chat/completions";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "Bearer " + openaiApiKey);

            String requestData = "{ \"messages\": [ { \"role\": \"system\", \"content\": \"You are a helpful assistant.\" }, { \"role\": \"user\", \"content\": \"" + question + "\" } ] }";

            HttpEntity<String> requestEntity = new HttpEntity<>(requestData, headers);

            responseEntity = new RestTemplate().exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

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
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = openaiApiKey; // API key goes here
        String model2 = "gpt-3.5-turbo"; // current model of chatgpt api

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer " + apiKey);
        con.setRequestProperty("Content-Type", "application/json");

        // Build the request body
        String body = "{\"model\": \"" + model2 + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + question + "\"}]}";
        con.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
        writer.write(body);
        writer.flush();
        writer.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // returns the extracted contents of the response.
        return extractContentFromResponse(response.toString());
    }


    public  String chatGPT(String message) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = openaiApiKey; // API key goes here
        String model = "gpt-3.5-turbo"; // current model of chatgpt api

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}