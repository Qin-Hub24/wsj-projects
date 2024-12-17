package com.app.raghu.service.impl.userPermissions;

import com.app.raghu.entity.userPermissions.UserDto;

import java.util.List;

public interface IUserService {
    /**
     * 新增用户
     * @param user 参数
     * @return
     */
    UserDto add(UserDto user);

    /**
     * 查询单个用户
     * @param userId 用户id
     * @return
     */
    UserDto getUser(Integer userId);

    /**
     * 查询所有用户
     * @return
     */
    List<UserDto> getAllUsers();


    /**
     * 修改用户
     * @param user 修改用户对象数据
     * @return
     */
    UserDto edit(UserDto user);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void delete(Integer userId);

}
