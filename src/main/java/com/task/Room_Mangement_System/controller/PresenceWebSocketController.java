package com.task.Room_Mangement_System.controller;
import com.task.Room_Mangement_System.dtos.JoinRoomDto;
import com.task.Room_Mangement_System.dtos.LeaveRoomDto;
import com.task.Room_Mangement_System.dtos.PingDto;
import com.task.Room_Mangement_System.dtos.PresenceMessageDto;
import com.task.Room_Mangement_System.service.PresenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import tools.jackson.databind.ObjectMapper;
import static com.task.Room_Mangement_System.Enums.MessageType.*;

@Controller
public class PresenceWebSocketController extends TextWebSocketHandler {

    private final PresenceService presenceService;
    private final ObjectMapper mapper = new ObjectMapper();

    public PresenceWebSocketController(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        PresenceMessageDto dto = mapper.readValue(message.getPayload(), PresenceMessageDto.class);

        switch (dto.getType()) {
            case JOIN -> {
                JoinRoomDto join = mapper.convertValue(dto.getPayload(), JoinRoomDto.class);
                presenceService.registerSession(join.getUsername(), join.getRoomId(), session);
            }
            case LEAVE -> {
                LeaveRoomDto leave = mapper.convertValue(dto.getPayload(), LeaveRoomDto.class);
                presenceService.removeUser(leave.getUsername());
            }
            case PING -> {
                PingDto ping = mapper.convertValue(dto.getPayload(), PingDto.class);
                presenceService.heartbeat(ping.getUsername());
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // unexpected disconnect handled gracefully
    }
}


