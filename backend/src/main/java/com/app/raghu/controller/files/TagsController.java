package com.app.raghu.controller.files;

import com.app.raghu.entity.files.Relations;
import com.app.raghu.entity.files.TagRelations;
import com.app.raghu.entity.files.Tags;
import com.app.raghu.service.files.TagRelationsService;
import com.app.raghu.service.files.TagsService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/files")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private TagRelationsService tagRelationsService;

    @GetMapping("/tagsDynamics")
    public Result getTagsDynamics( @RequestParam(required = false) String name) {
        return Result.success(tagsService.getTagsDynamics(name));
    }

    @PostMapping("/addTags")
    public Result setTagsAdd(@RequestBody Tags tags) {
        tags.setCreatedAt(LocalDateTime.now());
        if (tagsService.setTagsAdd(tags)) {
            return Result.success("添加成功");
        }
        return new Result(403, "error", "添加失败" + tags.getName() + "重复");
    }

    @DeleteMapping("/deleteTags")
    public Result setTagsDelete(@RequestBody Tags tags) {
        TagRelations tagRelations = new TagRelations();
        tagRelations.setTagId(tags);
        if (tagRelationsService.getExistsByTagId(tagRelations.getTagId())) {
            return new Result(403, "error", "该数据已经关联禁止删除");
        }
        if (tagsService.setTagsDelete(tags)) {
            return Result.success("删除成功");
        }
        return new Result(403, "error", "删除失败没有该id");
    }
}
