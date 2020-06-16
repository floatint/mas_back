package ru.vrn.medsys.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@ApiModel(value = "Компонент анализа")
@Setter
@Getter
@Entity
@Table(name = "analyses_components")
public class AnalysisComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    @ApiModelProperty(notes = "Id компонента")
    private Long id;
    @Column
    @ApiModelProperty(notes = "Навзание")
    private String name;
    @Column
    @ApiModelProperty(notes = "Строковое значение")
    private String strValue;
    @Column
    @ApiModelProperty(notes = "Числовое значение")
    private BigDecimal decValue;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "analysis_order_id", nullable = false)
    private AnalysisOrder order;
}
