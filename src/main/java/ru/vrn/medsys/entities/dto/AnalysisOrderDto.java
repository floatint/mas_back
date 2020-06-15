package ru.vrn.medsys.entities.dto;

import lombok.Data;
import ru.vrn.medsys.entities.AnalysisComponent;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class AnalysisOrderDto {
    Long id;
    AnalysisDto analysis;
    LocalDateTime date;
    UserDto customer;
    UserDto executor;
    Collection<AnalysisComponent> results;
}
