package com.tsa.injector.service;

import com.tsa.injector.argsparser.interfaces.PropertyReader;
import com.tsa.injector.database.DbConnector;
import com.tsa.injector.database.DbConnectorImpl;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class BaseServiceTest {

    protected DbConnector dbConnector;

    @BeforeEach
    void setUp() {
        PropertyReader propertyReader = mock(PropertyReader.class);
        when(propertyReader.getDbUrl()).thenReturn("jdbc:postgresql://localhost:5432/movie_land");
        when(propertyReader.getDbUserName()).thenReturn("postgres");
        when(propertyReader.getDbPassword()).thenReturn("password");

        dbConnector = new DbConnectorImpl(propertyReader);
    }
}
