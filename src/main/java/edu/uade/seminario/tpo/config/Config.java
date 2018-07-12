package edu.uade.seminario.tpo.config;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class Config {

    final static ConcurrentMap<String, Injector> injectors = new ConcurrentHashMap<>();

    final Logger log = LoggerFactory.getLogger(Config.class);

    public static void addInjector(String name, Injector injector) {
        injectors.put(name, injector);
    }

    public static Injector getInjector(String name) {
        return injectors.get(name);
    }

    public static <T> T safeGetInstance(String injectorName, Class<T> clazz) {
        try {
            final Injector injector = getInjector(injectorName);
            if (injector == null) return null;
            return injector.getInstance(clazz);
        } catch (ConfigurationException ex) {
            return null;
        }
    }

    /**
     * Remove named injector.
     */
    public static void clearInjector(String name) {
        injectors.remove(name);
    }

    /**
     * Remove all injectors.
     */
    public static void clearInjectors() {
        injectors.clear();
    }
}
