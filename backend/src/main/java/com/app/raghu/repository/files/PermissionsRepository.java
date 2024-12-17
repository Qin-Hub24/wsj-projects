package com.app.raghu.repository.files;

import com.app.raghu.entity.files.CaseFiles;
import com.app.raghu.entity.files.Permissions;
import com.app.raghu.entity.userPermissions.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionsRepository extends JpaRepository<Permissions, Integer> , JpaSpecificationExecutor<Permissions> {

    boolean existsByFileId(CaseFiles fileId);

    boolean existsByFileIdAndUserId(CaseFiles fileId, UserDto userId);

    void deleteByFileIdAndUserId(CaseFiles fileId, UserDto userId);
}
