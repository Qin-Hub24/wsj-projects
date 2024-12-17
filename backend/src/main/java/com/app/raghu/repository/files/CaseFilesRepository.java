package com.app.raghu.repository.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseFilesRepository extends JpaRepository<CaseFiles, Integer>, JpaSpecificationExecutor<CaseFiles> {

    boolean existsByCaseId(Cases caseFiles);
}
