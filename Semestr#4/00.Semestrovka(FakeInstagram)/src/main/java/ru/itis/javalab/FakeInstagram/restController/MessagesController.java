package ru.itis.javalab.FakeInstagram.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab.FakeInstagram.dto.MessageDto;
import ru.itis.javalab.FakeInstagram.service.interfaces.ChatService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Profile("mvc")
public class MessagesController {

    private Map<String, List<MessageDto>> messages = new HashMap<>();

    @Autowired
    private  ChatService chatService;



    @GetMapping(value = "/messages", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("pageId") String pageId
    ) {
        synchronized (messages.get(pageId)) {
            if (messages.get(pageId).isEmpty()) {
                try {
                    messages.get(pageId).wait();
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        List<MessageDto> response = new ArrayList<>(messages.get(pageId));
        messages.get(pageId).clear();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/messages", consumes = "application/json", headers = "content-type=application/x-www-form-urlencoded")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        if (!messages.containsKey(message.getPageId())) {
            messages.put(message.getPageId(), chatService.takeAllMessages());
        }
        chatService.addMessage(message);
        for (List<MessageDto> pageMessages : messages.values()) {
            synchronized (pageMessages) {
                pageMessages.add(message);
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }
}
