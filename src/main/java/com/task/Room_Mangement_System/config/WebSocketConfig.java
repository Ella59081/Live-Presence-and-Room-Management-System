package com.task.Room_Mangement_System.config;
import com.task.Room_Mangement_System.controller.PresenceWebSocketController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final PresenceWebSocketController controller;

    public WebSocketConfig(PresenceWebSocketController controller) {
        this.controller = controller;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(controller, "/ws/presence")
                .setAllowedOrigins("*");
    }
}

