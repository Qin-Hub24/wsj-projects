package com.app.raghu.controller.userPermissions;

import com.app.raghu.entity.userPermissions.UserDto;
import com.app.raghu.service.impl.userPermissions.IUserService;
import com.app.raghu.service.userPermissions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class User {

    // 依赖注入
    @Autowired
    private IUserService service;

    // 新增用户
    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserDto user) {
        UserDto userInfo = service.add(user);
        return ResponseEntity.ok(userInfo);
    }

    // 查询单个用户
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        UserDto userInfo = service.getUser(userId);
        return ResponseEntity.ok(userInfo);
    }

    // 查询所有用户
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUser = service.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    // 修改用户
    @PutMapping
    public ResponseEntity<UserDto> edit(@RequestBody UserDto user) {
        UserDto userInfo = service.edit(user);
        return ResponseEntity.ok(userInfo);
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Integer userId) {
        service.delete(userId);
        return ResponseEntity.ok("删除成功！");
    }
}
