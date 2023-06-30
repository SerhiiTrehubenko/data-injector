package com.tsa.injector.service;

import com.tsa.injector.dao.BaseDao;
import com.tsa.injector.dao.UserDao;
import com.tsa.injector.domain.UserDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceITest extends BaseServiceTest {
    @Test
    void shouldInsertOneUser() {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Serhii");
        userDto.setLastName("Trehubenko");
        userDto.setEmail("Trehubenko@gmail.com");
        userDto.setNickName("Trehubenko");

        List<UserDto> userDtos = List.of(userDto);

        BaseDao<UserDto> userDao = new UserDao(dbConnector);
        BaseService<UserDto> userService = new UserService(userDao);

        boolean result = userService.insert(userDtos);

        assertTrue(result);
    }
}
