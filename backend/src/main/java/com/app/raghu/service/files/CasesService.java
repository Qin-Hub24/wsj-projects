package com.app.raghu.service.files;

import com.app.raghu.entity.files.Cases;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface CasesService {

    // 添加案件
    void saveCases(Cases cases);

    // 查询案件编号是否已存在
    boolean existsByCaseNumber(String caseNumber);

    // 更新案件
    ArrayList<Cases> updateCase(Cases cases);

    // 删除案件
    boolean deleteCaseByCaseNumber(Integer id);

    // 查询案件
    Page<Cases> searchCases(String title, String caseNumber, String status, Pageable pageable);

    // 检查案件ID是否存在
    boolean existsById(Integer id);
}

