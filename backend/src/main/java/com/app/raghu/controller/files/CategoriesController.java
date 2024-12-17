package com.app.raghu.controller.files;

import com.app.raghu.entity.files.Categories;
import com.app.raghu.entity.files.Relations;
import com.app.raghu.service.files.CategoriesService;
import com.app.raghu.service.files.RelationService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/files")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private RelationService relationService;

    @GetMapping("/condition")
    public Result getConditionQuery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description
    ) {
        return Result.success(categoriesService.getConditionQuery(name, description));
    }

    @PostMapping("/addCategories")
    public Result setCategoriesAdd(@RequestBody Categories categories) {
        if (categoriesService.existsByName(categories.getName())) {
            return new Result(403, "error", "添加失败, 分类名重复");
        } else {
            LocalDateTime now = LocalDateTime.now();
            categories.setCreatedAt(now);
            categories.setUpdatedAt(now);
            categoriesService.setCategoriesAdd(categories);
            return Result.success("添加成功");
        }
    }

    @PutMapping("/modifyCategories")
    public Result setCategoriesModify(@RequestBody Categories categories) {
        List<Categories> modifyCategories = categoriesService.setCategoriesModify(categories);
        if (modifyCategories == null) {
            return new Result(403, "error", "修改失败该分类不存在");
        }
        return Result.success(modifyCategories);
    }

    @DeleteMapping("/{id}")
    public Result setCategoriesDelete(@PathVariable Integer id) {
        Relations relations = new Relations();
        Categories categories = new Categories();
        categories.setId(id);
        relations.setCategoryId(categories);
        if (relationService.getExistsByCategoryId(relations.getCategoryId())) {
            return new Result(403, "error", "该数据已经关联禁止删除");
        }
        if (categoriesService.setCategoriesDelete(id)) {
            return Result.success("删除成功");
        }
        return new Result(403, "error", "删除失败" + id + "不存在");
    }
}
