package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ru.vrn.medsys.entities.AnalysisComponent;

import java.util.Collection;

@ApiModel(value = "Анализ", description = "Данные анализа")
@Data
public class AnalysisDto {
    @ApiModelProperty(notes = "Id анализа")
    private Long id;
    @ApiModelProperty(notes = "Название анализа")
    private String name;
    @ApiModelProperty(notes = "Описание анализа")
    private String description;
}
