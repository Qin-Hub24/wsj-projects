package com.app.raghu.repository.files;

import com.app.raghu.entity.files.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CasesRepository extends JpaRepository<Cases, Integer>, JpaSpecificationExecutor<Cases> {

    boolean existsByCaseNumber(String caseNumber);

    boolean existsById(Integer id);

    Optional<Cases> findById(Integer id); // 根据ID查找案件

}
