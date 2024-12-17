package com.app.raghu.controller.files;

import com.app.raghu.entity.files.UserFiles;
import com.app.raghu.service.files.UserFilesService;
import com.app.raghu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/files")
public class UserFilesController {

    @Autowired
    private UserFilesService userFilesService;

    @PostMapping("/ufSave")
    public Result setUserFilesSave(@RequestBody UserFiles userFiles) {
        userFiles.setCreatedAt(LocalDateTime.now());
        System.out.println(userFiles);
        if (userFilesService.setUserFilesSave(userFiles)) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败!");
    }

    @GetMapping("/ufFind")
    public Result getUserFilesFind() {
        return Result.success(userFilesService.getUserFilesFind());
    }

    @DeleteMapping("/ufDelete/{id}")
    public Result deleteUserFilesById(@PathVariable Integer id) {
        if(userFilesService.deleteUserFilesById(id)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}
