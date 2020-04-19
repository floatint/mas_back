package ru.vrn.medsys.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "analyses")
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "analysis_id")
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    //@ElementCollection(targetClass = AnalysisComponent.class)
    //@CollectionTable(name = "analysis_components", joinColumns = @JoinColumn(name = "component_id"))
    //private Collection<AnalysisComponent> components;
    //@ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "analysis_component",joinColumns = @JoinColumn(name = "analysis_id", referencedColumnName = "analysis_id"),
    //        inverseJoinColumns = @JoinColumn(name = "component_id", referencedColumnName = "component_id"))
    //private Collection<AnalysisComponent> components;
}
