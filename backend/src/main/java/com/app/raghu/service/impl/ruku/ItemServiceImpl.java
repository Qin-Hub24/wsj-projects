package com.app.raghu.service.impl.ruku;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.raghu.entity.ruku.Item;
import com.app.raghu.repository.ruku.ItemRepository;
import com.app.raghu.service.ruku.IItemService;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private ItemRepository itemRepository;
    @Override
    public Integer saveRukuItem(Item item) {
        item.setCreatedAt(LocalDateTime.now());
        item.setUpdatedAt(LocalDateTime.now());
        return repository.save(item).getId();
    }

    @Override
    public Item findBySkuCode(String skuCode) {
        Optional<Item> item = repository.findBySkuCode(skuCode);
        return item.orElse(null);
    }

    @Override
    public void deleteRukuItem(Integer id) {
        repository.deleteById(id);  // 添加此实现
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return itemRepository.findById(id);
    }
}