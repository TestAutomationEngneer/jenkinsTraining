package org.example.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ConfigRetriever {

    public static Configuration configuration;
    static Logger logger = LoggerFactory.getLogger(ConfigRetriever.class);

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
                logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
            }
        }
        for (Map.Entry<String, Object> entry : configuration.getEnvironment().entrySet()) {
            if (System.getProperty(entry.getKey()) == null) {
                System.setProperty(entry.getKey(), entry.getValue().toString());
            }
        }
    }
}