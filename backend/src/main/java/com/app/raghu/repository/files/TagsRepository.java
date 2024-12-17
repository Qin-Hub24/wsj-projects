package com.app.raghu.repository.files;

import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Tags;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tags, Integer>, JpaSpecificationExecutor<Tags> {
    List<Tags> findAllByName(String name);

    boolean existsByName(String name);

}
