package com.app.raghu.service.factscaseservice.factsdetails;


import com.app.raghu.entity.casecirculation.factscaseservice.factsdetails.FactsCaseDetails;

import java.util.List;


/***
 * 模块名称：案情模块
 * 作者：莫康为、钟晓欣、唐永宏
 * 修改日期：20241112
 * 说明：案情模块接口实现
 * */
public interface FactsDetailsService {
    Integer saveCase(FactsCaseDetails caseEntity);
    List<FactsCaseDetails> getAllCases();
    FactsCaseDetails getCaseById(Integer id);
    void updateCase(FactsCaseDetails caseEntity);
    void deleteCaseById(Integer id);
}