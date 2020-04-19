package ru.vrn.medsys.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "analysis_orders")
public class AnalysisOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analysis_order_id")
    private Long id;
    @ElementCollection
    //@CollectionTable(name = "analyses", joinColumns = @JoinColumn(name = "analysis_id"))
    //private Collection<Analysis> analyses;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User executor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="analysis_result_id")
    private AnalysisResult result;

    private Collection<AnalysisComponent> results;
}
