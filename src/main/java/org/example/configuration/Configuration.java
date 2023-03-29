package org.example.configuration;

import java.util.HashMap;

public class Configuration {

    public HashMap<String, Object> globals;
    public HashMap<String, Object> env_int;
    public HashMap<String, Object> env_test;

    public Configuration() {
    }

    public HashMap<String, Object> getGlobals() {
        return globals;
    }

    public HashMap<String, Object> getEnv_int() {
        return env_int;
    }

    public HashMap<String, Object> getEnv_test() {
        return env_test;
    }

    public HashMap<String, Object> getEnvironment() {
        if (System.getProperty("environment").equals("env_test")) {
            return env_test;
        } else if (System.getProperty("environment").equals("env_int")) {
            return env_int;
        }
        throw new UnsupportedOperationException("Not supported environment selected.");
    }

    public Object getProperty(String property) {
        if (System.getProperty(property) == null) {
            return getGlobals().get(property);
        }
        return System.getProperty(property);
    }

    public boolean isSpecified(String property) {
        return System.getProperty(property) != null && getGlobals().get(property) != null;
    }
}