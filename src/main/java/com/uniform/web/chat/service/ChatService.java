package com.uniform.web.chat.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatgptService chatgptService;

    private final String initialMessage = "당신은 자기소개서 수정을 도와주는 사람입니다. 제가 자기소개서를 입력하면 자기소개서의 문맥, 문법을 교정하여 주어야 합니다. 그리고 그 외의 답변은 허용하지 않습니다";

    public String getChatResponse(String prompt) {
        // ChatGPT 에게 질문을 던집니다.
        return chatgptService.sendMessage(prompt);
    }
}