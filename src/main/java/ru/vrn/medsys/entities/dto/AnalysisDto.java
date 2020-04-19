package ru.vrn.medsys.entities.dto;

import lombok.Data;
import ru.vrn.medsys.entities.AnalysisComponent;

import java.util.Collection;

@Data
public class AnalysisDto {
    private Long id;
    private String name;
    private String description;
    private Collection<AnalysisComponent> components;
}
