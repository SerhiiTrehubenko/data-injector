package com.tsa.injector.dataparser;

import com.tsa.injector.domain.UserDto;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserParserTest {
    private final DataParser<UserDto> userParser = new UserParser();

    @Test
    void shouldParseTxtDataFileOfUsers() throws Exception {

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/user.txt");
        final File file = new File(resource.toURI());
        List<UserDto> userDtos = userParser.parse(file);

        assertNotNull(userDtos);
        assertEquals(10, userDtos.size());
    }

    //user-wrong-name.txt
    @Test
    void shouldThrowExceptionWhenUserNameHasWrongFormat() throws Exception {
        String expectedMessage = "UserDto{firstName='[WRONG FORMAT]', lastName='[WRONG FORMAT]', email='darlene.edwards15@example.com', nickName='bricks'}";

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/user-wrong-name.txt");
        final File file = new File(resource.toURI());

        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userParser.parse(file));
        assertEquals(expectedMessage, runtimeException.getMessage());
    }

    //user-wrong-email.txt
    @Test
    void shouldThrowExceptionWhenEmailHasWrongFormat() throws Exception {
        String expectedMessage = "UserDto{firstName='Габриэль', lastName='Джексон', email='[WRONG FORMAT]', nickName='hjkl'}";

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/user-wrong-email.txt");
        final File file = new File(resource.toURI());

        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userParser.parse(file));
        assertEquals(expectedMessage, runtimeException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNicknameHasWrongFormat() throws Exception {
        String expectedMessage = "UserDto{firstName='Габриэль', lastName='Джексон', email='gabriel.jackson91@example.com', nickName='[WRONG FORMAT]'}";

        final URL resource = Thread.currentThread().getContextClassLoader().getResource("data/user-wrong-nickname.txt");
        final File file = new File(resource.toURI());

        final RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> userParser.parse(file));
        assertEquals(expectedMessage, runtimeException.getMessage());
    }
}
