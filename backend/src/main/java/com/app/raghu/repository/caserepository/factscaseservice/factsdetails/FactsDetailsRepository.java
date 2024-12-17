package com.app.raghu.repository.caserepository.factscaseservice.factsdetails;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.raghu.entity.casecirculation.factscaseservice.factsdetails.FactsCaseDetails;


/***
 * 模块名称：案情模块
 * 作者：莫康为、钟晓欣、唐永宏
 * 修改日期：20241112
 * 说明：案情模块接口实现
 * */
public interface FactsDetailsRepository extends JpaRepository<FactsCaseDetails, Integer> {
    // 可以根据需要添加自定义查询方法
}
