package ru.vrn.medsys.entities.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class NewUserDto {
    private String name;
    private String email;
    private String password;
    private Collection<RoleDto> roles;
}
