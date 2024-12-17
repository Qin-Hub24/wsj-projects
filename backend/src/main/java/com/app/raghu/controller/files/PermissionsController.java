package com.app.raghu.controller.files;

import com.app.raghu.entity.files.Permissions;
import com.app.raghu.service.files.CaseFilesService;
import com.app.raghu.service.files.PermissionsService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/files")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private CaseFilesService caseFilesService;

    @PostMapping("/per_add")
    public Result setPermissionsAdd(@RequestBody Permissions permissions) {
        if (caseFilesService.getExistsByFileIdId(permissions.getFileId().getId())) {
            permissions.setCreatedAt(LocalDateTime.now());
            if (permissionsService.setPermissionsAdd(permissions)) {
                return new Result(403, "error", "添加失败,该文件已绑定");
            }
            return Result.success("添加成功");
        }
        return new Result(403, "error", "没有该文件");
    }

    @DeleteMapping("per_de")
    public Result setPermissionsDel(@RequestBody Permissions permissions) {
        if (permissionsService.setPermissionsDel(permissions)) {
            return Result.success("删除成功");
        }
        return new Result(403, "error", "删除失败没有该用户或文件");
    }

    @GetMapping("/per_find")
    public Result getPermissionsFindAll(@RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String permissionType) {
        return Result.success(permissionsService.getPermissionsFindAll(PageRequest.of(page, 10), permissionType));
    }
}
