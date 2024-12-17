package com.app.raghu.repository.files;

import com.app.raghu.entity.files.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoriesRepository extends JpaRepository<Categories, Integer>, JpaSpecificationExecutor<Categories> {
    boolean existsByName(String name);

}
