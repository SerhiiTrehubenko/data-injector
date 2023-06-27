package com.tsa.injector.database;


import com.tsa.injector.argsparser.interfaces.PropertyReader;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnectorImpl extends BasicDataSource implements DbConnector {

    public DbConnectorImpl(PropertyReader propertyReader) {
        super.setUrl(propertyReader.getDbUrl());
        super.setUsername(propertyReader.getDbUserName());
        super.setPassword(propertyReader.getDbPassword());
    }

    @Override
    public Connection getConnection() {
        try {
            return super.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
