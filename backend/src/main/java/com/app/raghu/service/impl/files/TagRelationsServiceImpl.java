package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.entity.files.Tags;
import com.app.raghu.repository.files.TagRelationsRepository;
import com.app.raghu.service.files.TagRelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TagRelationsServiceImpl implements TagRelationsService {

    @Autowired
    private TagRelationsRepository tagRelationsRepository;

    @Override
    public boolean setTagBinding(TagRelations tagRelations) {
        if (tagRelationsRepository.existsByFileIdAndTagId(tagRelations.getFileId(), tagRelations.getTagId())) {
            return false;
        }
        tagRelationsRepository.save(tagRelations);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteTagBinding(TagRelations tagRelations) {
        if (tagRelationsRepository.existsByFileIdAndTagId(tagRelations.getFileId(), tagRelations.getTagId())) {
            tagRelationsRepository.deleteByFileIdAndTagId(tagRelations.getFileId(), tagRelations.getTagId());
            return true;
        }
        return false;
    }

    @Override
    public Page<TagRelations> getFileAndTagAll(Pageable pageable) {
        return tagRelationsRepository.findAll(pageable);
    }

    @Override
    public boolean getExistsByFileId(CaseFiles fileId) {
        if (tagRelationsRepository.existsByFileId(fileId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean getExistsByTagId(Tags tagId) {
        if (tagRelationsRepository.existsByTagId(tagId)) {
            return true;
        }
        return false;
    }
}
