package com.app.raghu.service.userPermissions;

import com.app.raghu.entity.userPermissions.RoleDto;
import com.app.raghu.repository.userPermissions.RoleDtoRepository;
import com.app.raghu.service.impl.userPermissions.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {


    @Autowired
    private RoleDtoRepository repository;

    public RoleDto add(RoleDto role) {
    RoleDto roleInfo = new RoleDto();
        BeanUtils.copyProperties(role, roleInfo);
        return repository.save(roleInfo);
    }

    @Override
    public RoleDto getRole(Integer roleId) {
        return repository.findById(roleId).orElseThrow(() -> {
            throw new IllegalArgumentException("角色不存在，参数异常！");
        });
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return repository.findAll();
    }

    @Override
    public RoleDto edit(RoleDto role) {
        RoleDto roleInfo = new RoleDto();
        BeanUtils.copyProperties(role, roleInfo);
        return repository.save(roleInfo);
    }

    @Override
    public void delete(Integer roleId) {
        repository.deleteById(roleId);
    }
}
