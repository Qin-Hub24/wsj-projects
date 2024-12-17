package com.app.raghu.service.factscaseservice.factsdetails;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.casecirculation.factscaseservice.factsdetails.FactsCaseDetails;
import com.app.raghu.repository.caserepository.factscaseservice.factsdetails.FactsDetailsRepository;


/***
 * 模块名称：案情模块
 * 作者：莫康为、钟晓欣、唐永宏
 * 修改日期：20241112
 * 说明：案情模块接口实现
 * */
@Service
public class FactsDetailsServiceImpl implements FactsDetailsService {

    @Autowired
    private FactsDetailsRepository repository;

    public Integer saveCase(FactsCaseDetails caseEntity) {
        return repository.save(caseEntity).getId();
    }

    public List<FactsCaseDetails> getAllCases() {
        return repository.findAll();
    }

    public FactsCaseDetails getCaseById(Integer id) {
        Optional<FactsCaseDetails> optionalCase = repository.findById(id);
        return optionalCase.orElse(null);
    }

    public void updateCase(FactsCaseDetails caseEntity) {
        repository.save(caseEntity);
    }

    public void deleteCaseById(Integer id) {
        repository.deleteById(id);
    }
}