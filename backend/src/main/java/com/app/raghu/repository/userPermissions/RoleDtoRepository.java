package com.app.raghu.repository.userPermissions;

import com.app.raghu.entity.userPermissions.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleDtoRepository extends JpaRepository<RoleDto,Integer> {

}
