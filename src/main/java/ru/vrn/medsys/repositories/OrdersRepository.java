package ru.vrn.medsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vrn.medsys.entities.AnalysisOrder;

public interface OrdersRepository extends JpaRepository<AnalysisOrder, Long> {
}
