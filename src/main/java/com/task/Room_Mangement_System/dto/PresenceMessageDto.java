package com.task.Room_Mangement_System.dto;

import com.task.Room_Mangement_System.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresenceMessageDto {
    private MessageType type;
    private Object payload;
}

