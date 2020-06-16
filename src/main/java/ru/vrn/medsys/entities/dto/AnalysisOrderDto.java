package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.vrn.medsys.entities.AnalysisComponent;

import java.time.LocalDateTime;
import java.util.Collection;

@ApiModel(value = "Информация о анализе")
@Data
public class AnalysisOrderDto {
    @ApiModelProperty(notes = "Id заказа")
    Long id;
    @ApiModelProperty(notes = "Анализ")
    AnalysisDto analysis;
    @ApiModelProperty(notes = "Дата")
    LocalDateTime date;
    @ApiModelProperty(notes = "Заказчик")
    UserDto customer;
    @ApiModelProperty(notes = "Исполнитель")
    UserDto executor;
    @ApiModelProperty(notes = "Компоненты анализа")
    Collection<AnalysisComponent> results;
}
