package com.tsa.injector.domain;

import java.io.File;
import java.io.Serializable;
import java.util.Objects;

public enum DefaultProperty {
    DEFAULT_DATA("-data", "by default throws Exception, should be provided in time of App start up") {

        @Override
        public Serializable getProperty(Serializable parameter) {
            String pathToFolder = Objects.toString(parameter);
            final File folder = new File(pathToFolder);
            if (folder.isDirectory()) {
                return pathToFolder;
            }
            throw new RuntimeException("Provided path to data files: [%s] is not a path to a folder".formatted(pathToFolder));
        }
    },

    CONNECTION_PROPERTY("-connection", "application.properties") {
        @Override
        public Serializable getProperty() {
            return getProperty(property);
        }

        @Override
        public Serializable getProperty(Serializable parameter) {
            try (var input = DefaultProperty.class
                    .getClassLoader()
                    .getResourceAsStream(Objects.toString(parameter))) {
                java.util.Properties properties = new java.util.Properties();
                properties.load(input);
                return properties;
            } catch (Exception e) {
                throw new RuntimeException("The is no an [application.property] file by provided path [%s]\n CAUSE: %s"
                        .formatted(Objects.toString(parameter), e.toString()));
            }
        }
    };

    private final String tag;
    protected final Serializable property;

    DefaultProperty(String tag, Serializable property) {
        this.tag = tag;
        this.property = property;
    }

    public Serializable getProperty() {
        return property;
    }

    public abstract Serializable getProperty(Serializable parameter);

    public String getTag() {
        return tag;
    }

    public static DefaultProperty getDefaultProperty(String incomeTag) {
        for (DefaultProperty defaultProperty : DefaultProperty.values()) {
            if (defaultProperty.getTag().equals(incomeTag)) {
                return defaultProperty;
            }
        }
        throw new RuntimeException("The Provided tag: [%s] does not comply to Tag scheme".formatted(incomeTag));
    }
}
