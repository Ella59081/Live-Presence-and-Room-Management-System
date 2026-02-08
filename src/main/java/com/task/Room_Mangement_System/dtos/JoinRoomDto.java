package com.task.Room_Mangement_System.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class JoinRoomDto {
    private String username;
    private String roomId;
}

