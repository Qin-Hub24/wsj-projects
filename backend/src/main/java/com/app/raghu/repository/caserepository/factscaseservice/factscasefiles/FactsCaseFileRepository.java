package com.app.raghu.repository.caserepository.factscaseservice.factscasefiles;

import com.app.raghu.entity.casecirculation.factscaseservice.factscasefiles.FactsCaseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/***
 * 模块名称：案件档案模块
 * 作者：韦丽霞、陈晨
 * 修改日期：20241112
 * 说明：案件档案模块接口实现
 * */
@Repository
public interface FactsCaseFileRepository extends JpaRepository<FactsCaseFile, Long>  {
    List<FactsCaseFile> findByCaseId(Long caseId);

    List<FactsCaseFile> findByFileId(Long fileId);

    Optional<FactsCaseFile> findByCaseIdAndFileId(Long caseId, Long fileId);
}
