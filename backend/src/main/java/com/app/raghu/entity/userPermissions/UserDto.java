package com.app.raghu.entity.userPermissions;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true)
    private Integer id; // 用户id

    @Column(name = "username", unique = true, nullable = false)
    private String username; // 用户名

    @Column(name = "password", nullable = false)
    private String password; // 密码

    @Column(name = "email")
    private String email; // 邮箱

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 创建时间

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 修改时间

    @Column(name = "status", nullable = false)
    private Integer status; // 启用状态

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now(); // 在创建时设置创建和更新时间
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(); // 在更新时设置更新时间
    }
}
