package com.app.raghu.entity.files;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "file_tag_relations")
public class TagRelations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false, referencedColumnName = "id")
    private CaseFiles fileId;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false, referencedColumnName = "id")
    private Tags tagId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
