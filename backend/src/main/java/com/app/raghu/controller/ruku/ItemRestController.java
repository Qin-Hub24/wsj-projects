package com.app.raghu.controller.ruku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.raghu.entity.ruku.Item;
import com.app.raghu.service.ruku.IItemService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ruku")
public class ItemRestController {

  @Autowired
  private IItemService rukuItemService;

  // 创建 RukuItem
  @PostMapping("/save")
  public ResponseEntity<Map<String, Object>> saveRukuItem(@RequestBody Item item) {
    Integer id = rukuItemService.saveRukuItem(item);
    Map<String, Object> response = new HashMap<>();
    response.put("message", "RukuItem saved successfully");
    response.put("id", id);
    return ResponseEntity.ok(response);
  }

  // 根据 SKU 编码查找 RukuItem
  @GetMapping("/find/{skuCode}")
  public ResponseEntity<Item> findBySkuCode(@PathVariable String skuCode) {
    Item item = rukuItemService.findBySkuCode(skuCode);
    if (item != null) {
      return ResponseEntity.ok(item);
    } else {
      return ResponseEntity.status(404).body(null);
    }
  }

  // 更新 RukuItem
  @PutMapping("/update/{id}")
  public ResponseEntity<Map<String, Object>> updateRukuItem(@PathVariable Integer id, @RequestBody Item newItem) {
    Item existingItem = rukuItemService.findBySkuCode(newItem.getSkuCode());
    Map<String, Object> response = new HashMap<>();
    if (existingItem != null) {
      newItem.setId(id);
      rukuItemService.saveRukuItem(newItem);
      response.put("message", "RukuItem updated successfully");
      response.put("id", id);
      return ResponseEntity.ok(response);
    } else {
      response.put("message", "RukuItem with id " + id + " not found");
      return ResponseEntity.status(404).body(response);
    }
  }

  // 删除 RukuItem
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Map<String, Object>> deleteRukuItem(@PathVariable Integer id) {
    Map<String, Object> response = new HashMap<>();
    Item item = rukuItemService.findBySkuCode(id.toString()); // Adjust find method in service to support ID lookup.
    if (item != null) {
      rukuItemService.deleteRukuItem(id);
      response.put("message", "RukuItem deleted successfully");
      return ResponseEntity.ok(response);
    } else {
      response.put("message", "RukuItem with id " + id + " not found");
      return ResponseEntity.status(404).body(response);
    }
  }
}
