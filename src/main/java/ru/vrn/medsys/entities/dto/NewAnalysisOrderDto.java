package ru.vrn.medsys.entities.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel(value = "Заказ на проведение анализа")
@Data
public class NewAnalysisOrderDto {
    @ApiModelProperty(notes = "Id анализа")
    Long analysisId;
    @ApiModelProperty(notes = "Дата")
    LocalDateTime date;
    @ApiModelProperty(notes = "Id заказчика")
    Long customerId;
}
