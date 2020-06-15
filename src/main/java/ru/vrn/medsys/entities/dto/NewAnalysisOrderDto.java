package ru.vrn.medsys.entities.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewAnalysisOrderDto {
    Long analysisId;
    LocalDateTime date;
    Long customerId;
}
