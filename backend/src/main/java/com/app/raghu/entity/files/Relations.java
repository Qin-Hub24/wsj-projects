package com.app.raghu.entity.files;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "file_category_relations")
public class Relations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false, referencedColumnName = "id")
    private CaseFiles fileId;

    @ManyToOne
    @JoinColumn(name = "category_id",  referencedColumnName = "id")
    private Categories categoryId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
