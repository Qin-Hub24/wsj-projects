package com.app.raghu.service.factscaseservice.factscasefiles;

import com.app.raghu.entity.casecirculation.factscaseservice.factscasefiles.FactsCaseFile;

import java.util.List;
import java.util.Optional;

/***
 * 模块名称：案件档案模块
 * 作者：韦丽霞、陈晨
 * 修改日期：20241112
 * 说明：案件档案模块接口实现
 * */
public interface FactsCaseFileService {
    List<FactsCaseFile> findAll();

    List<FactsCaseFile> findByCaseId(Long caseId);

    List<FactsCaseFile> findByFileId(Long fileId);

    Optional<FactsCaseFile> findById(Long id);

    FactsCaseFile create(FactsCaseFile factsCaseFile);

    FactsCaseFile update(FactsCaseFile factsCaseFile);

    void delete(Long id);
}
