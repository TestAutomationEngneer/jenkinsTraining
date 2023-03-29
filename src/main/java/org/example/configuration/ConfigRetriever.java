package org.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ConfigRetriever {

    public static Configuration configuration;

    public static Configuration getConfig() throws IOException {
        if (configuration == null) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("configuration.yaml")).getFile());
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            configuration = om.readValue(file, Configuration.class);
            saveConfigAsSystemProperties(configuration);
            return configuration;
        }
        return configuration;
    }

    private static void saveConfigAsSystemProperties(Configuration configuration) {
        for (Map.Entry<String, Object> entry : configuration.getGlobals().entrySet()) {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue().toString());
            }
        }
        for (Map.Entry<String, Object> entry : configuration.getEnvironment().entrySet()) {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue().toString());
            }
        }
    }
}