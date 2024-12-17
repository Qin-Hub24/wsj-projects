package com.app.raghu.controller.casecirculation.factscaseservice.factsdetails;


import java.util.List;

import com.app.raghu.service.factscaseservice.factsdetails.FactsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.raghu.entity.casecirculation.factscaseservice.factsdetails.FactsCaseDetails;

/***
 * 模块名称：案情模块
 * 作者：莫康为、钟晓欣、唐永宏
 * 修改日期：20241112
 * 说明：案情模块接口实现
 * */
@RestController
@RequestMapping("/details")
public class FactsCaseController {

    @Autowired
    private FactsDetailsService service;

    @PostMapping
    public ResponseEntity<Integer> saveCase(@RequestBody FactsCaseDetails caseEntity) {
        Integer caseId = service.saveCase(caseEntity);
        return new ResponseEntity<>(caseId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FactsCaseDetails>> getAllCases() {
        List<FactsCaseDetails> cases = service.getAllCases();
        return new ResponseEntity<>(cases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactsCaseDetails> getCaseById(@PathVariable Integer id) {
        FactsCaseDetails caseEntity = service.getCaseById(id);
        if (caseEntity!= null) {
            return new ResponseEntity<>(caseEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateCase(@RequestBody FactsCaseDetails caseEntity) {
        service.updateCase(caseEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaseById(@PathVariable Integer id) {
        service.deleteCaseById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
