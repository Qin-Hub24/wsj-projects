package com.app.raghu.service.impl.files;

import com.app.raghu.entity.files.UserFiles;
import com.app.raghu.repository.files.UserFilesRepository;
import com.app.raghu.service.files.UserFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFilesServiceImpl  implements UserFilesService {

    @Autowired
    private UserFilesRepository userFilesRepository;

    @Override
    public boolean setUserFilesSave(UserFiles userFiles) {
        userFilesRepository.save(userFiles);
        return true;
    }

    @Override
    public List<UserFiles> getUserFilesFind() {
        return userFilesRepository.findAll();
    }

    @Override
    public boolean deleteUserFilesById(Integer id) {
        if (userFilesRepository.existsById(id)) {
            userFilesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
