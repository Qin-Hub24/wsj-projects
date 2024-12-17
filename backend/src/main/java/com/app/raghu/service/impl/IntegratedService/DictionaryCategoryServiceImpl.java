//第4组：7.“综合业务”功能-7.“业务入口”模块
package com.app.raghu.service.impl.IntegratedService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.IntegratedService.DictionaryCategory;
import com.app.raghu.repository.IntegratedService.DictionaryCategoryRepository;
import com.app.raghu.service.IntegratedService.IDictionaryCategoryService;

@Service
public class DictionaryCategoryServiceImpl implements IDictionaryCategoryService {

    @Autowired
    private DictionaryCategoryRepository dictionaryCategoryRepository;

    @Override
    public List<DictionaryCategory> getAllCategories() {
        return dictionaryCategoryRepository.findAll();
    }

    @Override
    public void saveCategory(DictionaryCategory category) {
        dictionaryCategoryRepository.save(category);
    }

    // 可以根据需要添加其他方法的实现
}