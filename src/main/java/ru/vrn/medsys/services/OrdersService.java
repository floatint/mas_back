package ru.vrn.medsys.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vrn.medsys.entities.AnalysisOrder;
import ru.vrn.medsys.repositories.OrdersRepository;

import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository repository){
        ordersRepository = repository;
    }

    public Optional<AnalysisOrder> findById(Long id){
        return ordersRepository.findById(id);
    }

    public Iterable<AnalysisOrder> findAll(){
        return ordersRepository.findAll();
    }

    public AnalysisOrder save(AnalysisOrder a){
        return ordersRepository.save(a);
    }

    public Iterable<AnalysisOrder> saveAll(Iterable<AnalysisOrder> a){
        return ordersRepository.saveAll(a);
    }

    public void delete(AnalysisOrder a){
        ordersRepository.delete(a);
    }


}