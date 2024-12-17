package com.app.raghu.service.factscaseservice.factscourts;




import com.app.raghu.entity.casecirculation.factscaseservice.factscourts.Court;

import java.util.List;
import java.util.Optional;


/***
 * 模块名称：法院模块
 * 作者：蒙学忠、梁蕊珍
 * 修改日期：20241112
 * 说明：法院模块接口实现
 * */
public interface ICourtService {

    // 保存法院信息
    Integer saveCourt(Court court);

    // 查找所有法院信息
    List<Court> findAllCourts();

    // 根据ID查找法院信息
    Optional<Court> findById(Integer id);

    // 更新法院信息
    Integer updateCourt(Integer id, Court court);

    // 删除法院信息
    void deleteCourt(Integer id);
}
