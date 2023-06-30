package com.tsa.injector;

import com.tsa.injector.argsparser.impl.DefaultAppArgsPropertyContext;
import com.tsa.injector.argsparser.impl.DefaultArgsParser;
import com.tsa.injector.argsparser.impl.DefaultPropertyReader;
import com.tsa.injector.argsparser.interfaces.AppArgsPropertyContext;
import com.tsa.injector.argsparser.interfaces.ArgsParser;
import com.tsa.injector.argsparser.interfaces.PropertyReader;
import com.tsa.injector.dao.*;
import com.tsa.injector.database.DbConnector;
import com.tsa.injector.database.DbConnectorImpl;
import com.tsa.injector.datainjector.DataInjector;
import com.tsa.injector.datainjector.DataInjectorImpl;
import com.tsa.injector.dataparser.*;
import com.tsa.injector.domain.*;
import com.tsa.injector.file.FileFetcher;
import com.tsa.injector.file.FileHolder;
import com.tsa.injector.file.FileHolderImpl;
import com.tsa.injector.file.TxtFileFetcher;
import com.tsa.injector.service.*;

public class Starter {
    public static void main(String[] args) {
//        ArgsParse
        AppArgsPropertyContext argsPropertyContext = new DefaultAppArgsPropertyContext(DefaultProperty.DEFAULT_DATA, DefaultProperty.CONNECTION_PROPERTY);
        ArgsParser argsParser = new DefaultArgsParser(argsPropertyContext);
        argsParser.parse(args);

        AppArgsPropertyContext configuredContext = argsParser.getContext();

//        Property Reader
        PropertyReader propertyReader = new DefaultPropertyReader(configuredContext);

//        DB Connection
        DbConnector connector = new DbConnectorImpl(propertyReader);

//        Data Files Fetch
        String pathDataFiles = propertyReader.getPathDataFiles();
        FileFetcher fileFetcher = new TxtFileFetcher(pathDataFiles);
        FileHolder filesHolder = new FileHolderImpl(fileFetcher.getFiles());

//        Parsers
        ParserFactory parserFactory = new ParserFactoryImpl();

//        DAO
        DaoFactory daoFactory = new DaoFactoryImpl(connector);

//        Services
        ServiceFactory serviceFactory = new ServiceFactoryImpl(daoFactory);

//        MAIN Data Injector Handler
        DataInjector dataInjector = new DataInjectorImpl(filesHolder, parserFactory, serviceFactory);
        dataInjector.inject();
        System.out.println("Injection processed Successfully");
    }
}
