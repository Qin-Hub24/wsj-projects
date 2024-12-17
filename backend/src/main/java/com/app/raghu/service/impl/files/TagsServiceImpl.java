package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Tags;
import com.app.raghu.repository.files.TagsRepository;
import com.app.raghu.service.files.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;


    @Override
    public List<Tags> getTagsDynamics(String name) {
        Specification<Tags> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return tagsRepository.findAll(spec);
    }

    @Override
    public boolean setTagsAdd(Tags tags) {
        if (tagsRepository.existsByName(tags.getName())) {
            return false;
        }
        tagsRepository.save(tags);
        return true;
    }

    @Override
    public boolean setTagsDelete(Tags tags) {
        if (tagsRepository.existsById(tags.getId())) {
            tagsRepository.delete(tags);
            return true;
        }
        return false;

    }
}
