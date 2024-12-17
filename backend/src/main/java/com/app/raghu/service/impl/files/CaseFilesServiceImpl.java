package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Cases;
import com.app.raghu.repository.files.CaseFilesRepository;
import com.app.raghu.repository.files.CasesRepository;
import com.app.raghu.repository.userPermissions.UserDtoRepository;
import com.app.raghu.service.files.CaseFilesService;
import com.app.raghu.util.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class CaseFilesServiceImpl implements CaseFilesService {
    @Autowired
    private CaseFilesRepository caseFilesRepository;

    @Autowired
    private CasesRepository casesRepository;

    @Autowired
    private UserDtoRepository userDtoRepository;

    @Autowired
    private FileStorage fileStorage;

    @Override
    public boolean getExistsByFileIdId(Integer id) {
        if (caseFilesRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public CaseFiles setAddCaseFiles(MultipartFile file, Integer caseId, Integer uploadedById, String description) {
        try {
            String relativePath = fileStorage.storeFile(file);

            CaseFiles caseFile = new CaseFiles();
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && originalFilename.contains(".")) {
                String nameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
                caseFile.setName(nameWithoutExtension);
            } else {
                // 如果没有后缀或为空，直接使用原始名称
                caseFile.setName(originalFilename);
            }
            caseFile.setPath(relativePath); // 存储相对路径
            caseFile.setSize(file.getSize());
            caseFile.setFileType(file.getContentType());
            caseFile.setCreatedAt(LocalDateTime.now());
            caseFile.setUpdatedAt(LocalDateTime.now());
            caseFile.setIsCurrent(true);
            caseFile.setDescription(description);
            caseFile.setVersion(1);

            caseFile.setCaseId(casesRepository.findById(caseId).orElseThrow(() ->
                    new RuntimeException("Case ID not found: " + caseId))
            );
            caseFile.setUploadedBy(userDtoRepository.findById(uploadedById).orElseThrow(() ->
                    new RuntimeException("User ID not found: " + uploadedById))
            );

            return caseFilesRepository.save(caseFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    @Override
    public void deleteCaseFileById(Integer id, Integer userId) {
        // 查找文件
        CaseFiles caseFile = caseFilesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found: " + id));

        // 判断用户是否是文件的创建者
        if (!caseFile.getUploadedBy().getId().equals(userId)) {
            throw new RuntimeException("403: error - 没有操作权限");
        }

        // 删除本地文件
        String filePath = caseFile.getPath();
        if (filePath != null) {
            boolean isDeleted = fileStorage.deleteFile(filePath);
            if (!isDeleted) {
                throw new RuntimeException("Failed to delete file from storage: " + filePath);
            }
        }

        // 删除数据库记录
        caseFilesRepository.delete(caseFile);
    }

    @Override
    public Page<CaseFiles> searchCaseFiles(
            String name,
            String fileType,
            String description,
            Integer version,
            Boolean isCurrent,
            Integer userId,
            Pageable pageable
    ) {
        Specification<CaseFiles> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 限制只查询当前用户上传的文件
            predicates.add(criteriaBuilder.equal(root.get("uploadedBy").get("id"), userId));
            // 按文件名过滤
            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            // 按文件类型过滤
            if (fileType != null && !fileType.isEmpty()) {
                predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("fileType")), fileType.toLowerCase()));
            }
            // 按描述过滤
            if (description != null && !description.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + description.toLowerCase() + "%"));
            }
            // 按版本过滤
            if (version != null) {
                predicates.add(criteriaBuilder.equal(root.get("version"), version));
            }
            // 按是否当前版本过滤
            if (isCurrent != null) {
                predicates.add(criteriaBuilder.equal(root.get("isCurrent"), isCurrent));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return caseFilesRepository.findAll(specification, pageable);
    }

    @Override
    public CaseFiles updateCaseFileWithUpload(
            MultipartFile file,
            Integer fileId,
            Integer caseId,
            Integer userId,
            String description
    ) {
        // 查找文件
        CaseFiles existingFile = caseFilesRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found: " + fileId));

        // 判断用户是否是文件的创建者
        if (!existingFile.getUploadedBy().getId().equals(userId)) {
            throw new RuntimeException("403: error - 没有操作权限");
        }

        try {
            // 替换文件：删除旧文件后存储新文件
            if (file != null) {
                fileStorage.UpdatedFile(existingFile.getPath()); // 删除旧文件
                String newPath = fileStorage.storeFile(file);  // 存储新文件
                existingFile.setPath(newPath);
                existingFile.setName(file.getOriginalFilename());
                existingFile.setSize(file.getSize());
                existingFile.setFileType(file.getContentType());
            }

            // 更新其他字段
            existingFile.setDescription(description);
            existingFile.setUpdatedAt(LocalDateTime.now());
            existingFile.setIsCurrent(true);
            existingFile.setVersion(existingFile.getVersion() + 1);

            // 更新所属案件（如果提供了 caseId）
            if (caseId != null) {
                existingFile.setCaseId(casesRepository.findById(caseId)
                        .orElseThrow(() -> new RuntimeException("Case ID not found: " + caseId)));
            }

            // 保存更新
            return caseFilesRepository.save(existingFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update file", e);
        }
    }

    @Override
    public boolean getExistsByCaseId(Integer id) {
        CaseFiles caseFiles = new CaseFiles();
        Cases cases = new Cases();
        cases.setId(id);
        caseFiles.setCaseId(cases);
        if (caseFilesRepository.existsByCaseId(caseFiles.getCaseId())) {
            return true;
        }
        return false;
    }

}