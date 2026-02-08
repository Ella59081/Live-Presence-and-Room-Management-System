package com.task.Room_Mangement_System.service.impl;
import com.task.Room_Mangement_System.model.UserSession;
import com.task.Room_Mangement_System.service.PresenceService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PresenceServiceImpl implements PresenceService {

    private final Map<String, UserSession> onlineUsers = new ConcurrentHashMap<>();
    private final Map<String, Set<String>> roomUsers = new ConcurrentHashMap<>();
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void registerSession(String username, String roomId, WebSocketSession session) {
        onlineUsers.put(username, new UserSession(username, roomId, Instant.now()));
        roomUsers.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(username);
        sessions.put(session.getId(), session);
    }

    public void removeUser(String username) {
        UserSession user = onlineUsers.remove(username);
        if (user != null) {
            roomUsers.getOrDefault(user.getRoomId(), Set.of()).remove(username);
        }
    }

    public void heartbeat(String username) {
        UserSession user = onlineUsers.get(username);
        if (user != null) {
            user.setLastSeen(Instant.now());
        }
    }

    public Set<String> getRoomPresence(String roomId) {
        return roomUsers.getOrDefault(roomId, Set.of());
    }

    public Set<String> getOnlineUsers() {
        return onlineUsers.keySet();
    }
}

