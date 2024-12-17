/**第4组：7.“综合业务”功能-7.“业务入口”模块**/
package com.app.raghu.service.IntegratedService;

import java.util.List;

import com.app.raghu.entity.IntegratedService.DictionaryCategory;

public interface IDictionaryCategoryService {

    List<DictionaryCategory> getAllCategories();

    void saveCategory(DictionaryCategory category);

    // 可以根据需要添加其他方法，如根据ID获取分类、更新分类、删除分类等
}