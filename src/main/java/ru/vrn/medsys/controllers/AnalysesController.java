package ru.vrn.medsys.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vrn.medsys.entities.Analysis;
import ru.vrn.medsys.entities.dto.AnalysisDto;
import ru.vrn.medsys.services.AnalysesService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/analyses")
public class AnalysesController {
    private final AnalysesService analysesService;
    private final ModelMapper mapper;

    @Autowired
    public AnalysesController(AnalysesService service, ModelMapper modelMapper){
        analysesService = service;
        mapper = modelMapper;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Iterable<AnalysisDto>> getAllAnalyses() {
        //get list of dto type
        Type responseListType = new TypeToken<List<AnalysisDto>>(){}.getType();
        return ResponseEntity.ok(mapper.map(analysesService.findAll(), responseListType));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AnalysisDto> getAnalysis(@PathVariable Long id){
        Optional<Analysis> analysis = analysesService.findById(id);
        if (!analysis.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mapper.map(analysis.get(), AnalysisDto.class));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AnalysisDto> createAnalysis(@RequestBody AnalysisDto newAnalysis){
        Analysis a = analysesService.save(mapper.map(newAnalysis, Analysis.class));
        return ResponseEntity.ok(mapper.map(a, AnalysisDto.class));
    }

    //TODO: Есть сомнения, что можно сделать по другому
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AnalysisDto> updateAnalysis(@PathVariable Long id, @RequestBody AnalysisDto newAnalysis){
        Optional<Analysis> analysisOptional = analysesService.findById(id);
        if (!analysisOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        newAnalysis.setId(analysisOptional.get().getId());
        Analysis tmp = mapper.map(newAnalysis, Analysis.class);
        return ResponseEntity.ok(mapper.map(analysesService.save(tmp), AnalysisDto.class));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<AnalysisDto> deleteAnalysis(@PathVariable Long id){
        Optional<Analysis> a = analysesService.findById(id);
        if (!a.isPresent()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        analysesService.delete(a.get());
        return ResponseEntity.ok(mapper.map(a.get(), AnalysisDto.class));
    }
}
