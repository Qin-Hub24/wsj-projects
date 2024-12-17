package com.app.raghu.repository.files;

import com.app.raghu.entity.files.UserFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFilesRepository extends JpaRepository<UserFiles, Integer> {
}
