package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.Permissions;
import com.app.raghu.entity.files.Tags;
import com.app.raghu.repository.files.PermissionsRepository;
import com.app.raghu.service.files.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    private PermissionsRepository permissionsRepository;


    @Override
    public boolean setPermissionsAdd(Permissions permissions) {
        System.out.println(permissionsRepository.existsByFileId(permissions.getFileId()));
        if (permissionsRepository.existsByFileId(permissions.getFileId())) {
            return true;
        }
        permissionsRepository.save(permissions);
        return false;
    }

    @Override
    @Transactional
    public boolean setPermissionsDel(Permissions permissions) {
        if (permissionsRepository.existsByFileIdAndUserId(permissions.getFileId(), permissions.getUserId())) {
            permissionsRepository.deleteByFileIdAndUserId(permissions.getFileId(), permissions.getUserId());
            return true;
        }
        return false;
    }

    @Override
    public Page<Permissions> getPermissionsFindAll(Pageable pageable, String permissionType) {
        Specification<Permissions> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (permissionType != null && !permissionType.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("permissionType")), "%" + permissionType.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return permissionsRepository.findAll(spec, pageable);
    }
}
