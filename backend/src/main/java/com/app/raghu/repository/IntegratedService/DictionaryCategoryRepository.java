/**第4组：7.“综合业务”功能-7.“业务入口”模块**/
package com.app.raghu.repository.IntegratedService;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.raghu.entity.IntegratedService.DictionaryCategory;

public interface DictionaryCategoryRepository extends JpaRepository<DictionaryCategory, Integer> {
    // 可以根据需要添加自定义查询方法
}
