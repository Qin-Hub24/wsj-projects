package com.app.raghu.service.files;


import com.app.raghu.entity.files.CaseFiles;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CaseFilesService {
    boolean getExistsByFileIdId(Integer id);

    CaseFiles setAddCaseFiles(MultipartFile file, Integer caseId, Integer uploadedById, String description);

    void deleteCaseFileById(Integer id, Integer userId);

    Page<CaseFiles> searchCaseFiles(String name, String fileType, String description, Integer version, Boolean isCurrent, Integer userId, Pageable pageable);

    CaseFiles updateCaseFileWithUpload(MultipartFile file, Integer fileId, Integer caseId, Integer userId, String description);

    boolean getExistsByCaseId(Integer id);
}