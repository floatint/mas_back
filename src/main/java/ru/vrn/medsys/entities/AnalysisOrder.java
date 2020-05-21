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
    //@ElementCollection
    //@CollectionTable(name = "analyses", joinColumns = @JoinColumn(name = "analysis_id"))
    //private Collection<Analysis> analyses;
    @ManyToOne
    @JoinColumn(name = "analysis_id", nullable = false)
    private Analysis analysis;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    })
    private User customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name="executor_id", referencedColumnName = "user_id")
    })
    //@JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User executor;
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name ="analysis_result_id")
    //private AnalysisResult result;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<AnalysisComponent> results;
}
