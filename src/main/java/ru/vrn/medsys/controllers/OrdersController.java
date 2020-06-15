package ru.vrn.medsys.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vrn.medsys.entities.AnalysisOrder;
import ru.vrn.medsys.entities.dto.AnalysisOrderDto;
import ru.vrn.medsys.entities.dto.NewAnalysisOrderDto;
import ru.vrn.medsys.services.OrdersService;
import ru.vrn.medsys.services.UsersService;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final UsersService usersService;
    private final ModelMapper mapper;

    @Autowired
    public OrdersController(OrdersService service, UsersService userService, ModelMapper modelMapper){
        ordersService = service;
        usersService = userService;
        mapper = modelMapper;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AnalysisOrderDto> addOrder(@RequestBody NewAnalysisOrderDto newOrder) {
        //check order dto
        return null;
        //if (!usersService.findById(newOrder.getCustomerId()).isPresent()) {
        //    return ResponseEntity.status(HttpStatus.NOT_FOUND);
        //}
        //AnalysisOrder order = ordersService.save(newOrder);

        //return ResponseEntity.ok(mapper.map(order, AnalysisOrderDto.class));
    }
}
