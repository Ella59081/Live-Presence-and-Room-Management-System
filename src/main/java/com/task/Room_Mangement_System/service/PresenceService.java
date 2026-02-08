package com.task.Room_Mangement_System.service;

import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

public interface PresenceService {
    public void registerSession(String username, String roomId, WebSocketSession session);
    public void removeUser(String username);
    public void heartbeat(String userName);
    public Set<String> getRoomPresence(String roomId);
    public Set<String> getOnlineUsers();
}
