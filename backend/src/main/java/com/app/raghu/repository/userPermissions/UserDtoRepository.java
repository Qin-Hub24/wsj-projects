package com.app.raghu.repository.userPermissions;

import com.app.raghu.entity.userPermissions.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDtoRepository extends JpaRepository<UserDto,Integer> {

}
