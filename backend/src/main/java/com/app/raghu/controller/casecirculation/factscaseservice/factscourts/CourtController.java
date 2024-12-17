package com.app.raghu.controller.casecirculation.factscaseservice.factscourts;



import com.app.raghu.service.factscaseservice.factscourts.ICourtService;
import com.app.raghu.entity.casecirculation.factscaseservice.factscourts.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/***
 * 模块名称：法院模块
 * 作者：蒙学忠、梁蕊珍
 * 修改日期：20241112
 * 说明：法院模块接口实现
 * */
@RestController
@RequestMapping("/facts_courts")
public class CourtController {

    @Autowired
    private ICourtService courtService;

    // 获取所有法院信息
    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts() {
        List<Court> courts = courtService.findAllCourts();
        return new ResponseEntity<>(courts, HttpStatus.OK);
    }

    // 根据ID获取法院信息
    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourtById(@PathVariable Integer id) {
        Optional<Court> court = courtService.findById(id);
        if (court.isPresent()) {
            return new ResponseEntity<>(court.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 添加法院
    @PostMapping
    public ResponseEntity<Court> createCourt(@RequestBody Court court) {
        Integer courtId = courtService.saveCourt(court);
        return ResponseEntity.status(HttpStatus.CREATED).body(court);
    }

    // 更新法院信息
    @PutMapping("/{id}")
    public ResponseEntity<Court> updateCourt(@PathVariable Integer id, @RequestBody Court court) {
        Integer updatedCourtId = courtService.updateCourt(id, court);
        if (updatedCourtId != null) {
            return new ResponseEntity<>(court, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 删除法院信息
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourt(@PathVariable Integer id) {
        courtService.deleteCourt(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

