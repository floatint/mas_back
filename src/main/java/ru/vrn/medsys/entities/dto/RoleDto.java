package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Роль", description = "Роль пользователя")
@Data
public class RoleDto {
    @ApiModelProperty(notes = "Название роли")
    private String name;
}
