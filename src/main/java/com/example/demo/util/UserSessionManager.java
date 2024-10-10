package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserSessionManager {
    private static final Map<UUID, Long> userSessions = new HashMap<>();

    // Call this method when a user logs in
    public static UUID addUser(Long userId) {
        UUID sessionId = UUID.randomUUID();
        userSessions.put(sessionId, userId);
        return sessionId;
    }

    // Call this method when a user logs out
    public static void removeUser(UUID sessionId) {
        userSessions.remove(sessionId);
    }

    // Get user ID by session ID
    public static Long getUserId(UUID sessionId) {
        return userSessions.get(sessionId);
    }
}
