//第4组：7.“综合业务”功能-7.“业务入口”模块
package com.app.raghu.service.impl.IntegratedService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.IntegratedService.DictionaryItems;
import com.app.raghu.repository.IntegratedService.DictionaryItemsRepository;
import com.app.raghu.service.IntegratedService.IDictionaryItemsService;

@Service
public class DictionaryItemsServiceImpl implements IDictionaryItemsService {

    @Autowired
    private DictionaryItemsRepository dictionaryItemsRepository;

    @Override
    public List<DictionaryItems> getAllItems() {
        return dictionaryItemsRepository.findAll();
    }

    @Override
    public void saveItem(DictionaryItems item) {
        dictionaryItemsRepository.save(item);
    }

    // 可以根据需要添加其他方法的实现
}