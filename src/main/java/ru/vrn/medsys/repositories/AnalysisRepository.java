package ru.vrn.medsys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vrn.medsys.entities.Analysis;

public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
}
