package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.domain.UserDto;

import java.util.List;

public class UserService implements BaseService<UserDto> {
    private final BaseDao<UserDto> userDao;

    public UserService(BaseDao<UserDto> userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean insert(List<UserDto> userDtos) {
        userDtos.stream()
                .filter(userDao::notPresent)
                .forEach(userDao::insert);
        return true;
    }
}
