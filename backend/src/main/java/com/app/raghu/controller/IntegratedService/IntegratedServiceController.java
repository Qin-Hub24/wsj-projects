//第4组：7.“综合业务”功能-7.“业务入口”模块
package com.app.raghu.controller.IntegratedService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.raghu.entity.IntegratedService.DictionaryCategory;
import com.app.raghu.entity.IntegratedService.DictionaryItems;

import com.app.raghu.service.IntegratedService.IDictionaryCategoryService;
import com.app.raghu.service.IntegratedService.IDictionaryItemsService;

@RestController
@RequestMapping("/integrated-service")
public class IntegratedServiceController {

    @Autowired
    private IDictionaryCategoryService dictionaryCategoryService;

    @Autowired
    private IDictionaryItemsService dictionaryItemsService;

    @GetMapping("/categories")
    public ResponseEntity<List<DictionaryCategory>> getDictionaryCategories() {
        List<DictionaryCategory> categories = dictionaryCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/category")
    public ResponseEntity<String> saveDictionaryCategory(@RequestBody DictionaryCategory category) {
        dictionaryCategoryService.saveCategory(category);
        return ResponseEntity.ok("Category saved successfully");
    }

    @GetMapping("/items")
    public ResponseEntity<List<DictionaryItems>> getDictionaryItems() {
        List<DictionaryItems> items = dictionaryItemsService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/item")
    public ResponseEntity<String> saveDictionaryItem(@RequestBody DictionaryItems item) {
        dictionaryItemsService.saveItem(item);
        return ResponseEntity.ok("Item saved successfully");
    }
}
