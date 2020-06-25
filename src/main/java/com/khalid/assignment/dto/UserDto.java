package com.khalid.assignment.dto;

import com.khalid.assignment.bean.User;
import lombok.Data;

import java.time.Instant;

@Data
public class UserDto {

    private String name;
    private Instant createdAt;
    private String role;

    public UserDto() {
    }

    public UserDto(User user) {
        this.name = user.getUserName();
        this.createdAt = user.getCreatedAt();
        this.role = user.getRole();

    }
}
