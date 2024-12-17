package com.app.raghu.service.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.entity.files.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagRelationsService {
    boolean setTagBinding(TagRelations tagRelations);

    boolean deleteTagBinding(TagRelations tagRelations);

    Page<TagRelations> getFileAndTagAll(Pageable pageable);

    boolean getExistsByFileId(CaseFiles fileId);

    boolean getExistsByTagId(Tags tagId);
}
