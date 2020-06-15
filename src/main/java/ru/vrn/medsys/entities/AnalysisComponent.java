package ru.vrn.medsys.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "analyses_components")
public class AnalysisComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;
    @Column
    private String name;
    @Column
    private String strValue;
    @Column
    private BigDecimal decValue;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "analysis_order_id", nullable = false)
    private AnalysisOrder order;
}
