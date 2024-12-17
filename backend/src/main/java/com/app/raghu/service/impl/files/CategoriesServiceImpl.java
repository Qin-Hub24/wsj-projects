package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Relations;
import com.app.raghu.repository.files.CategoriesRepository;
import com.app.raghu.repository.files.RelationsRepository;
import com.app.raghu.service.files.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;



    @Override
    public List<Categories> getConditionQuery(String name, String description) {
        Specification<Categories> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (description != null && !description.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return categoriesRepository.findAll(spec);
    }

    @Override
    public void setCategoriesAdd(Categories categories) {
        categoriesRepository.save(categories);
    }

    @Override
    public boolean existsByName(String name) {
        return categoriesRepository.existsByName(name);
    }

    @Override
    public List<Categories> setCategoriesModify(Categories categories) {
        Categories modify = categoriesRepository.findById(categories.getId()).orElse(null);
        if (modify == null) {
            return null;
        }
        modify.setName(categories.getName());
        modify.setDescription(categories.getDescription());
        modify.setUpdatedAt(LocalDateTime.now());
        ArrayList<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(categoriesRepository.save(modify));
        return categoriesList;
    }

    @Override
    public boolean setCategoriesDelete(Integer id) {
        if (categoriesRepository.existsById(id)) {
            categoriesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
