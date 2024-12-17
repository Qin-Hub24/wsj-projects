package com.app.raghu.entity.files;


import com.app.raghu.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "case_file_permissions")
public class UserFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false, referencedColumnName = "id")
    private CaseFiles fileId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User userId;

    @Column(name = "permission_type")
    private String permissionType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
