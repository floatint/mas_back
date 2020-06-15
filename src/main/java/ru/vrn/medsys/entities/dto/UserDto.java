package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@ApiModel(value = "Пользователь", description = "Данные пользователя")
@Data
public class UserDto {
    @ApiModelProperty(notes = "Id пользователя")
    private Long id;
    @ApiModelProperty(notes = "Имя пользователя")
    private String name;
    @ApiModelProperty(notes = "Email пользователя")
    private String email;
    @ApiModelProperty(notes = "Роли пользователя")
    private Set<String> roles;
}
