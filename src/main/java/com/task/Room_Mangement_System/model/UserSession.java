package com.task.Room_Mangement_System.model;
import java.time.Instant;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSession {
    private String username;
    private String roomId;
    private Instant lastSeen;

}
