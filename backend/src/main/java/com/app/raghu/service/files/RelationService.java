package com.app.raghu.service.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Relations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RelationService {
    boolean setBindingAdd(Relations relations);

    void setUnbindingDelete(Relations relations);

    Page<Relations> getRQuery(Pageable pageable);

    boolean getExistsByFileId(CaseFiles fileId);

    boolean getExistsByCategoryId(Categories categoryId);
}
