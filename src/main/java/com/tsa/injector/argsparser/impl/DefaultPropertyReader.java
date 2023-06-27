package com.tsa.injector.argsparser.impl;

import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.argsparser.interfaces.PropertyReader;
import com.tsa.injector.domain.DefaultProperty;

import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;

public class DefaultPropertyReader implements PropertyReader {

    private static final String DB_URL = "db-url";
    private static final String USER_NAME = "db-user";
    private static final String PASSWORD = "db-password";

    private final Properties properties;
    private final String pathDataFiles;

    public DefaultPropertyReader(AppArgsPropertyContext variablesContext) {
        Serializable incomeProperties = variablesContext.getProperty(DefaultProperty.CONNECTION_PROPERTY);
        if (Properties.class.isAssignableFrom(incomeProperties.getClass())) {
            this.properties = (Properties) incomeProperties;
        } else {
            throw new RuntimeException("Provided object: [%s] is not the [%s] type".formatted(incomeProperties.getClass().getName(),
                    Properties.class.getName()));
        }
        this.pathDataFiles = (String) variablesContext.getProperty(DefaultProperty.DEFAULT_DATA);
    }
    @Override
    public String getDbUrl() {
        String dbUrl = properties.getProperty(DB_URL);
        Objects.requireNonNull(dbUrl, "A Data Base URL is absent");

        return dbUrl;
    }

    @Override
    public String getDbUserName() {
        String dbUserName = properties.getProperty(USER_NAME);
        Objects.requireNonNull(dbUserName, "A Data Base USER NAME is absent");
        return dbUserName;
    }

    @Override
    public String getDbPassword() {
        String dbPassword = properties.getProperty(PASSWORD);
        Objects.requireNonNull(dbPassword, "A Data Base PASSWORD is absent");
        return dbPassword;
    }

    @Override
    public String getPathDataFiles() {
        return pathDataFiles;
    }
}
