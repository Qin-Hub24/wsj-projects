package com.app.raghu.repository.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Relations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RelationsRepository extends JpaRepository<Relations, Integer>, JpaSpecificationExecutor<Relations> {

    void deleteByFileIdAndCategoryId(CaseFiles fileId, Categories categoryId);

    boolean existsByFileIdAndCategoryId(CaseFiles fileId, Categories categoryId);

    boolean existsByFileId(CaseFiles fileId);

    boolean existsByCategoryId(Categories categoryId);
}
