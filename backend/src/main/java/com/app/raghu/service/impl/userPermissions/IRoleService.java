package com.app.raghu.service.impl.userPermissions;

import com.app.raghu.entity.userPermissions.RoleDto;
import com.app.raghu.entity.userPermissions.UserDto;

import java.util.List;

public interface IRoleService {
    /**
     * 新增角色
     * @param role 角色参数
     * @return
     */
    RoleDto add(RoleDto role);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return
     */
    RoleDto getRole(Integer roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleDto> getAllRoles();


    /**
     *
     * @param role 角色参数
     * @return
     */
    RoleDto edit(RoleDto role);

    /**
     * 删除角色
     * @param roleId 角色id
     */
    void delete(Integer roleId);

}
