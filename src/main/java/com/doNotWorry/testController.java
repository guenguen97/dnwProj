//package com.doNotWorry;
//
//import io.github.flashvayne.chatgpt.property.ChatgptProperties;
//import io.github.flashvayne.chatgpt.service.ChatgptService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//
//@RequiredArgsConstructor
//@RestController
//@Slf4j
//@RequestMapping("/api/v1/chat-gpt")
//public class testController {
//    private final ChatService chatService;
//    private final ChatgptService chatgptService;
//
//    @Autowired
//    private ChatgptProperties chatgptProperties;
//    @Value("${chatgpt}")
//    private String openaiApiKey;
//
//    //chat-gpt 와 간단한 채팅 서비스 소스
//    @PostMapping("")
//    @ResponseBody
//    public String test(@RequestBody String question) {
//        chatgptProperties.setApiKey(openaiApiKey);
//        System.out.println("채팅 받기 성공");
//        System.out.println(question);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + openaiApiKey);
//
//        // Create a RestTemplate instance
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Create the request entity with headers and body
//        org.springframework.http.HttpEntity<String> requestEntity = new org.springframework.http.HttpEntity<>(question, headers);
//
//        // Send the POST request and get the response
//        return restTemplate.postForObject("https://api.openai.com/v1/engines/davinci/completions", requestEntity, String.class);
//        //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
//    }
//
//}