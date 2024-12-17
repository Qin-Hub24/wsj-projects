package com.app.raghu.repository.ruku;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.raghu.entity.ruku.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Item> findBySkuCode(String skuCode);
}
