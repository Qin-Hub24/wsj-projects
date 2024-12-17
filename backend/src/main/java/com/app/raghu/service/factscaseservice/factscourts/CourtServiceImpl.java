package com.app.raghu.service.factscaseservice.factscourts;




import com.app.raghu.entity.casecirculation.factscaseservice.factscourts.Court;
import com.app.raghu.service.factscaseservice.factscourts.ICourtService;
import com.app.raghu.repository.caserepository.factscaseservice.factscourts.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/***
 * 模块名称：法院模块
 * 作者：蒙学忠、梁蕊珍
 * 修改日期：20241112
 * 说明：法院模块接口实现
 * */
@Service
public class CourtServiceImpl implements ICourtService {

    @Autowired
    private CourtRepository courtRepository;

    @Override
    public Integer saveCourt(Court court) {
        // 设置创建时间
        court.setCreatedAt(java.time.LocalDateTime.now());
        return courtRepository.save(court).getId();
    }

    @Override
    public List<Court> findAllCourts() {
        return courtRepository.findAll();
    }

    @Override
    public Optional<Court> findById(Integer id) {
        return courtRepository.findById(id);
    }

    @Override
    public Integer updateCourt(Integer id, Court court) {
        Optional<Court> existingCourt = courtRepository.findById(id);
        if (existingCourt.isPresent()) {
            Court updateCourt = existingCourt.get();
            updateCourt.setName(court.getName());
            updateCourt.setAddress(court.getAddress());
            updateCourt.setContactNumber(court.getContactNumber());
            updateCourt.setUpdatedAt(java.time.LocalDateTime.now());
            return courtRepository.save(updateCourt).getId();
        } else {
            return null;
        }
    }

    @Override
    public void deleteCourt(Integer id) {
        courtRepository.deleteById(id);
    }
}

