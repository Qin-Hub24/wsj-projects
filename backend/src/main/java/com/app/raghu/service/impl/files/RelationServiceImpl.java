package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Relations;
import com.app.raghu.repository.files.RelationsRepository;
import com.app.raghu.service.files.RelationService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RelationsRepository relationsRepository;

    @Override
    public boolean setBindingAdd(Relations relations) {
        if (relationsRepository.existsByFileIdAndCategoryId(relations.getFileId(), relations.getCategoryId())) {
            return false;
        }
        relationsRepository.save(relations);
        return true;
    }

    @Override
    @Transactional
    public void setUnbindingDelete(Relations relations) {
        if (relationsRepository.existsByFileIdAndCategoryId(relations.getFileId(), relations.getCategoryId())) {
            relationsRepository.deleteByFileIdAndCategoryId(relations.getFileId(), relations.getCategoryId());
        }
    }

    @Override
    public Page<Relations> getRQuery(Pageable pageable) {
        return relationsRepository.findAll(pageable);
    }


    @Override
    public boolean getExistsByFileId(CaseFiles fileId) {
        if (relationsRepository.existsByFileId(fileId)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean getExistsByCategoryId(Categories categoryId) {
        if (relationsRepository.existsByCategoryId(categoryId)) {
            return true;
        }
        return false;
    }
}
