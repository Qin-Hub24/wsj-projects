package com.app.raghu.controller.files;

import com.app.raghu.entity.files.Relations;
import com.app.raghu.service.files.RelationService;
import com.app.raghu.util.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/files")
public class RelationsController {
    @Autowired
    private RelationService relationService;

    @PostMapping("/binding")
    public Result setBindingAdd(@RequestBody Relations relations) {
        relations.setCreatedAt(LocalDateTime.now());
        if (relationService.setBindingAdd(relations)) {
            return Result.success("绑定成功");
        }
        return Result.success("该文件或文件夹已绑定");

    }

    @DeleteMapping("/unbind")
    public Result setUnbindingDelete(@RequestBody Relations relations) {
        relationService.setUnbindingDelete(relations);
        return Result.success("解绑成功");
    }
    @GetMapping("/queryR")
    public Result getRQueryAll(@RequestParam(defaultValue = "0") int page) {
        return Result.success(relationService.getRQuery(PageRequest.of(page, 10)));
    }
}
