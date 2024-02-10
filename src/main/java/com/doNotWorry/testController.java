package com.doNotWorry;

import io.github.flashvayne.chatgpt.property.ChatgptProperties;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class testController {
    private final ChatService chatService;
    private final ChatgptService chatgptService;

    @Autowired
    private ChatgptProperties chatgptProperties;
    @Value("${chatgpt}")
    private String openaiApiKey;

    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("")
    @ResponseBody
    public String test(@RequestBody String question) {
        chatgptProperties.setApiKey(openaiApiKey);
        System.out.println("채팅 받기 성공");
        System.out.println(question);
        return chatService.getChatResponse(question);
        //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }

}