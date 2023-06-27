package com.tsa.injector.dataparser;

import com.tsa.injector.domain.UserDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserParser extends CyclicDataParser<UserDto> {
    private final static int FIRST_NAME = 0;
    private final static int LAST_NAME = 1;
    private final static Pattern USER_NAME = Pattern.compile("^\\p{L}{3,50} \\p{L}{3,50}");
    private final static Pattern EMAIL = Pattern.compile("^(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+$");
    private final static Pattern NICKNAME = Pattern.compile("^[\\w\\d]{3,50}$");

    public UserParser() {
        methods.add(this::resolveUserName);
        methods.add(this::resolveEmail);
        methods.add(this::resolveNickname);
        dataFieldsNumber = methods.size();
    }

    private UserDto userDto = new UserDto();
    protected void checkCycle() {
        if (completedCycle()) {
            dtos.add(checkFullness());
            userDto = new UserDto();
        }
    }

    private boolean completedCycle() {
        return stepsNumber % dataFieldsNumber == 0;
    }

    private void resolveUserName(String trimmedLine) {
        Matcher matcherUserName = USER_NAME.matcher(trimmedLine);
        if (matcherUserName.matches()) {
            String[] userName = trimmedLine.split(" ");
            userDto.setFirstName(userName[FIRST_NAME]);
            userDto.setLastName(userName[LAST_NAME]);
        }
    }

    private void resolveEmail(String trimmedLine) {
        Matcher matcherEmail = EMAIL.matcher(trimmedLine);
        if (matcherEmail.matches()) {
            userDto.setEmail(trimmedLine);
        }
    }

    private void resolveNickname(String trimmedLine) {
        Matcher matcherNickname = NICKNAME.matcher(trimmedLine);
        if (matcherNickname.matches()) {
            userDto.setNickName(trimmedLine);
        }
    }

    private UserDto checkFullness() {
        if (userDto.isFull()) {
            return userDto;
        }
        throw new RuntimeException(userDto.toString());
    }
}
