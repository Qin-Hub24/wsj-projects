package com.app.raghu.service.files;

import com.app.raghu.entity.files.UserFiles;

import java.util.List;

public interface UserFilesService {

    boolean setUserFilesSave(UserFiles userFiles);

    List<UserFiles> getUserFilesFind();

    boolean deleteUserFilesById(Integer id);
}
