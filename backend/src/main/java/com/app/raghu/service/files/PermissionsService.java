package com.app.raghu.service.files;

import com.app.raghu.entity.files.Permissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

public interface PermissionsService {
    boolean setPermissionsAdd(Permissions permissions);

    boolean setPermissionsDel(Permissions permissions);

    Page<Permissions> getPermissionsFindAll(Pageable pageable, String permissionType);
}
