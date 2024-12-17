package com.app.raghu.service.files;

import com.app.raghu.entity.files.Categories;

import java.util.List;

public interface CategoriesService {

    List<Categories> getConditionQuery(String name, String description);

    void setCategoriesAdd(Categories categories);

    boolean existsByName(String name);

    List<Categories> setCategoriesModify(Categories categories);

    boolean setCategoriesDelete(Integer id);
}
