package com.tsa.injector.database;

import java.sql.Connection;

public interface DbConnector {
    Connection getConnection();
}
