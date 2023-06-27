package com.tsa.injector;

import com.tsa.injector.argsparser.impl.DefaultAppArgsPropertyContext;
import com.tsa.injector.argsparser.impl.DefaultArgsParser;
import com.tsa.injector.argsparser.impl.DefaultPropertyReader;
import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.argsparser.interfaces.ArgsParser;
import com.tsa.injector.argsparser.interfaces.PropertyReader;
import com.tsa.injector.database.DbConnector;
import com.tsa.injector.database.DbConnectorImpl;
import com.tsa.injector.domain.DefaultProperty;
import com.tsa.injector.file.FileFetcher;
import com.tsa.injector.file.TxtFileFetcher;

import java.sql.Connection;
import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) {
//        ArgsParse
        AppArgsPropertyContext argsPropertyContext = new DefaultAppArgsPropertyContext(DefaultProperty.DEFAULT_DATA, DefaultProperty.CONNECTION_PROPERTY);
        ArgsParser argsParser = new DefaultArgsParser(argsPropertyContext);
        argsParser.parse(args);

        AppArgsPropertyContext configuredContext = argsParser.getContext();
        PropertyReader propertyReader = new DefaultPropertyReader(configuredContext);

//        DB Connection
        DbConnector connector = new DbConnectorImpl(propertyReader);
        try (Connection connection = connector.getConnection()) {
            System.out.println(!connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        Data Files Fetch
        String pathDataFiles = propertyReader.getPathDataFiles();
        FileFetcher fileFetcher = new TxtFileFetcher(pathDataFiles);
    }
}
