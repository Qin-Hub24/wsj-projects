package com.app.raghu.service.ruku;



import com.app.raghu.entity.ruku.Item;

import java.util.Optional;

public interface IItemService {
    Integer saveRukuItem(Item item);

    Item findBySkuCode(String skuCode);

    void deleteRukuItem(Integer id);

    Optional<Item> findById(Integer id);
}