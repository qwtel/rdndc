package com.tumblr.cell303.rdndc.creator;

import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Creator {

    private final static Map<Class<?>, List<Class<?>>> cache =
            new ConcurrentHashMap<Class<?>, List<Class<?>>>(0, 0.9f, Runtime.getRuntime().availableProcessors());

    protected static List<Class<?>> getImplementations(Class<?> base) {

        List<Class<?>> implementations = cache.get(base);
        if (implementations == null) {
            implementations = getAnnotatedImplementations(base);
            cache.put(base, implementations);
        }

        return implementations;
    }

    private static List<Class<?>> getAnnotatedImplementations(Class<?> base) {
        String packageName = base.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> allAnnotated = reflections.getTypesAnnotatedWith(Redundancy.class);

        List<Class<?>> implementations = new ArrayList<Class<?>>();
        for (Class<?> clazz : allAnnotated) {
            Redundancy redundancy = clazz.getAnnotation(Redundancy.class);
            if (redundancy.value().equals(base)) {
                implementations.add(clazz);
            }
        }

        // XXX: Add the class itself if possible
        if(!base.isInterface() && !Modifier.isAbstract(base.getModifiers())) {
            implementations.add(base);
        }

        return implementations;
    }
}
