package com.app.raghu.controller.files;

import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.service.files.TagRelationsService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/files")
public class TagRelationsController {

    @Autowired
    private TagRelationsService tagRelationsService;

    @PostMapping("/tagBinding")
    public Result setTagBinding(@RequestBody TagRelations tagRelations) {
        tagRelations.setCreatedAt(LocalDateTime.now());
        if (tagRelationsService.setTagBinding(tagRelations)) {
            return Result.success("绑定成功");
        }
        return new Result(403, "error", "该标签或文件已绑定");
    }

    @DeleteMapping("/tagUnbind")
    public Result deleteTagBinding(@RequestBody TagRelations tagRelations) {
        if (tagRelationsService.deleteTagBinding(tagRelations)) {
            return Result.success("删除成功");
        }
        return new Result(403, "error", "该数据未存在");
    }

    @GetMapping("/fileAndTag")
    public Result getFileAndTagAll(@RequestParam(defaultValue = "0") int page) {
        return Result.success(tagRelationsService.getFileAndTagAll(PageRequest.of(page, 10)));
    }
}
