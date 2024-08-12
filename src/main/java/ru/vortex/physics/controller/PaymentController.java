package ru.vortex.physics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.vortex.physics.model.Ball;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class PaymentController {
    @GetMapping("/getHello")
    @ResponseBody
    public String getHello() {
        return "Hello World, Пицца моцарелла, пицца моцарелла, релла релла релла";
    }
    @PostMapping("/postHello")
    @ResponseBody
    public String postHello() {
        return "Hello World, Это ебаный пост запрос, детка";
    }
}
