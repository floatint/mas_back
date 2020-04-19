package ru.vrn.medsys.entities.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Collection<RoleDto> roles;
}
