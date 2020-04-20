package ru.vrn.medsys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vrn.medsys.entities.Analysis;
import ru.vrn.medsys.repositories.AnalysisRepository;

import java.util.Optional;

@Service
public class AnalysesService {

    private final AnalysisRepository analysisRepository;

    @Autowired
    public AnalysesService(AnalysisRepository repository){
        analysisRepository = repository;
    }

    public Optional<Analysis> findById(Long id){
        return analysisRepository.findById(id);
    }

    public Iterable<Analysis> findAll(){
        return analysisRepository.findAll();
    }

    public Analysis save(Analysis a){
        return analysisRepository.save(a);
    }

    public Iterable<Analysis> saveAll(Iterable<Analysis> a){
        return analysisRepository.saveAll(a);
    }

    public void delete(Analysis a){
        analysisRepository.delete(a);
    }


}
