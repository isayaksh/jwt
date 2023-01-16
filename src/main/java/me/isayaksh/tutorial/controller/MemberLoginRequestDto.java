package me.isayaksh.tutorial.controller;

import lombok.Data;

@Data
public class MemberLoginRequestDto {
    private String username;
    private String password;
}
