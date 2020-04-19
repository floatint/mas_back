package ru.vrn.medsys.entities;

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

    private String strValue;
    private BigDecimal decValue;
    //@ManyToMany(mappedBy = "components")
    //private Collection<Analysis> analyses;
}
