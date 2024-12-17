package com.app.raghu.repository.caserepository.factscaseservice.factscourts;


import com.app.raghu.entity.casecirculation.factscaseservice.factscourts.Court;
import org.springframework.data.jpa.repository.JpaRepository;


/***
 * 模块名称：法院模块
 * 作者：蒙学忠、梁蕊珍
 * 修改日期：20241112
 * 说明：法院模块接口实现
 * */
public interface CourtRepository extends JpaRepository<Court, Integer> {
    // 你可以在这里添加其他自定义查询方法
}
