package com.app.raghu.service.userPermissions;

import com.app.raghu.entity.userPermissions.UserDto;
import com.app.raghu.repository.userPermissions.UserDtoRepository;
import com.app.raghu.service.impl.userPermissions.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserDtoRepository repository;

    @Autowired
    private BCryptPasswordEncoder pwdEncoder;


    public UserDto add(UserDto user) {
        user.setPassword(pwdEncoder.encode(user.getPassword()));
        UserDto userPojo = new UserDto();
        BeanUtils.copyProperties(user, userPojo);
        return repository.save(userPojo);
    }

    public UserDto getUser(Integer userId) {
        return repository.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("用户不存在，参数异常！");
        });
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserDto edit(UserDto user) {
        UserDto userInfo = new UserDto();
        BeanUtils.copyProperties(user, userInfo);
        return repository.save(userInfo);
    }

    @Override
    public void delete(Integer userId) {
        repository.deleteById(userId);
    }
}
