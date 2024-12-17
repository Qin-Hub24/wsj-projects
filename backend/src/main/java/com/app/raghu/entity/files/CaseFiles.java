package com.app.raghu.entity.files;

import com.app.raghu.entity.userPermissions.UserDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "case_files")
public class CaseFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false, referencedColumnName = "id")
    private Cases caseId;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "path", length = 500, nullable = false)
    private String path;

    @Column(name = "size")
    private Long size;

    @Column(name = "file_type", length = 50)
    private String fileType;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false, referencedColumnName = "id")
    private UserDto uploadedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "version")
    private Integer version;

    @Column(name = "is_current")
    private Boolean isCurrent;
}
