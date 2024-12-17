package com.app.raghu.repository.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.entity.files.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRelationsRepository extends JpaRepository<TagRelations, Integer>, JpaSpecificationExecutor<TagRelations> {
    boolean existsByFileIdAndTagId(CaseFiles fileId, Tags tagId);

    void deleteByFileIdAndTagId(CaseFiles fileId, Tags tagId);

    boolean existsByFileId(CaseFiles fileId);

    boolean existsByTagId(Tags tagId);
}
