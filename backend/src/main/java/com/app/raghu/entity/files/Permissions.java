package com.app.raghu.entity.files;

import com.app.raghu.entity.userPermissions.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "case_file_permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false, referencedColumnName = "id")
    private CaseFiles fileId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserDto userId;

    @Column(name = "permission_type", length = 50)
    private String permissionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
