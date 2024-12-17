package com.app.raghu.entity.files;

import lombok.Data;

import javax.persistence.*;
        import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "file_tags")
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
