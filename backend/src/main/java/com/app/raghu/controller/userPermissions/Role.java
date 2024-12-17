package com.app.raghu.controller.userPermissions;

import com.app.raghu.entity.userPermissions.RoleDto;
import com.app.raghu.entity.userPermissions.UserDto;
import com.app.raghu.service.impl.userPermissions.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class Role {

    // 依赖注入
    @Autowired
    private IRoleService service;

    // 新增角色
    @PostMapping
    public ResponseEntity<RoleDto> add(@RequestBody RoleDto role) {
        RoleDto roleInfo = service.add(role);
        return ResponseEntity.ok(roleInfo);
    }

    // 查询单个角色
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Integer roleId) {
        RoleDto roleInfo = service.getRole(roleId);
        return ResponseEntity.ok(roleInfo);
    }

    // 查询所有角色
    @GetMapping("/allRoles")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> allUser = service.getAllRoles();
        return ResponseEntity.ok(allUser);
    }

    // 修改角色
    @PutMapping
    public ResponseEntity<RoleDto> edit(@RequestBody RoleDto role) {
        RoleDto roleInfo = service.edit(role);
        return ResponseEntity.ok(roleInfo);
    }

    // 删除角色
    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> delete(@PathVariable Integer roleId) {
        service.delete(roleId);
        return ResponseEntity.ok("删除成功！");
    }
}
