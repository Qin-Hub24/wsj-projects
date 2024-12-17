package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.Cases;
import com.app.raghu.repository.files.CasesRepository;
import com.app.raghu.service.files.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CasesServiceImpl implements CasesService {

    @Autowired
    private CasesRepository casesRepository;

    @Override
    public void saveCases(Cases cases) {
        if (cases.getStatus().equals("未完成")) {
            cases.setStatus("0");
        } else if (cases.getStatus().equals("已完成")) {
            cases.setStatus("1");
        }
        casesRepository.save(cases);
    }

    @Override
    public boolean existsByCaseNumber(String caseNumber) {
        return casesRepository.existsByCaseNumber(caseNumber);
    }

    @Override
    public ArrayList<Cases> updateCase(Cases cases) {
        if (cases.getStatus().equals("未完成")) {
            cases.setStatus("0");
        } else if (cases.getStatus().equals("已完成")) {
            cases.setStatus("1");
        }
        // 先通过ID查找案件
        Optional<Cases> existingCase = casesRepository.findById(cases.getId());
        if (!existingCase.isPresent()) {
            throw new RuntimeException("案件不存在");
        }

        Cases caseToUpdate = existingCase.get();

        // 判断caseNumber是否改变，如果改变则验证是否存在相同的案件编号
        if (!caseToUpdate.getCaseNumber().equals(cases.getCaseNumber())) {
            if (casesRepository.existsByCaseNumber(cases.getCaseNumber())) {
                return null;
            }
        }

        // 更新案件的字段
        caseToUpdate.setCaseNumber(cases.getCaseNumber());
        caseToUpdate.setTitle(cases.getTitle());
        caseToUpdate.setDescription(cases.getDescription());
        caseToUpdate.setStatus(cases.getStatus());
        caseToUpdate.setUpdatedAt(LocalDateTime.now());

        // 保存更新后的案件
        casesRepository.save(caseToUpdate);

        // 返回更新后的案件
        return new ArrayList<>(List.of(caseToUpdate));
    }


    @Override
    public boolean deleteCaseByCaseNumber(Integer id) {
        // 检查案件是否存在并删除
        if (casesRepository.existsById(id)) {
            casesRepository.deleteById(id);
            return true;
        }
        return false;  // 返回false，表示没有找到要删除的案件
    }

    @Override
    public Page<Cases> searchCases(String title, String caseNumber, String status, Pageable pageable) {
        Specification<Cases> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (title != null && !title.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (caseNumber != null && !caseNumber.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("caseNumber")), "%" + caseNumber.toLowerCase() + "%"));
            }
            if (status != null && !status.isEmpty()) {
                if (status.equals("open")) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), "0"));
                } else if (status.equals("closed")) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), "1"));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return casesRepository.findAll(specification, pageable);
    }

    @Override
    public boolean existsById(Integer id) {
        return casesRepository.existsById(id);
    }
}