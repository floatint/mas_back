package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

@ApiModel(value = "Новый пользователь", description = "Данные нового пользователя")
@Data
public class NewUserDto {
    @ApiModelProperty(notes = "Имя пользователя")
    private String name;
    @ApiModelProperty(notes = "Email пользователя")
    private String email;
    @ApiModelProperty(notes = "Пароль")
    private String password;
    @ApiModelProperty(notes = "Роли пользователя")
    private Collection<RoleDto> roles;
}
