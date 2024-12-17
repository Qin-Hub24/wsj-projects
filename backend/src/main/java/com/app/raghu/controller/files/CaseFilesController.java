package com.app.raghu.controller.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Relations;
import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.service.files.CaseFilesService;
import com.app.raghu.service.files.RelationService;
import com.app.raghu.service.files.TagRelationsService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/files")
public class CaseFilesController {

    @Autowired
    private CaseFilesService caseFilesService;

    @GetMapping("/sh_caseFile")
    public Result searchCaseFiles(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "fileType", required = false) String fileType,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "version", required = false) Integer version,
            @RequestParam(value = "isCurrent", required = false) Boolean isCurrent,
            @RequestParam("userId") Integer userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<CaseFiles> caseFilesPage = caseFilesService.searchCaseFiles(
                    name, fileType, description, version, isCurrent, userId, pageable
            );
            return Result.success(caseFilesPage);
        } catch (Exception e) {
            return new Result(500, "error", "查询失败：" + e.getMessage());
        }
    }


    @PostMapping("/addCaseFile")
    public Result setAddCaseFiles(
            @RequestParam("file") MultipartFile file,
            @RequestParam("caseId") Integer caseId,
            @RequestParam("uploadedBy") Integer uploadedById,
            @RequestParam(value = "description", required = false) String description
    ) {
        try {
            CaseFiles caseFile = caseFilesService.setAddCaseFiles(file, caseId, uploadedById, description);

            // 拼接完整 URL
            String fileUrl = "http://localhost:9090/" + caseFile.getPath();
            return Result.success(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(403, "error", e.getMessage());
        }
    }


    @DeleteMapping("/d_caseFile/{id}")
    public Result deleteCaseFile(@PathVariable Integer id, @RequestParam("userId") Integer userId) {
        try {
            caseFilesService.deleteCaseFileById(id, userId);
            return Result.success("删除成功");
        } catch (RuntimeException e) {
            if (e.getMessage().startsWith("403")) {
                return new Result(403, "error", "没有操作权限");
            }
            return new Result(500, "error", e.getMessage());
        }
    }

    @PutMapping("/up_caseFile")
    public Result updateCaseFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("caseId") Integer caseId,
            @RequestParam("uploadedBy") Integer userId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("fileId") Integer fileId // 文件 ID
    ) {
        try {
            CaseFiles updatedFile = caseFilesService.updateCaseFileWithUpload(file, fileId, caseId, userId, description);
            // 返回更新后的文件信息
            return new Result(200, "更新成功", updatedFile);
        } catch (RuntimeException e) {
            if (e.getMessage().startsWith("403")) {
                return new Result(403, "error", "没有操作权限");
            }
            return new Result(500, "error", e.getMessage());
        }
    }


}