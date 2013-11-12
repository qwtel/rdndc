package com.tumblr.cell303.rdndnc.type;

import com.tumblr.cell303.rdndnc.method.Invoker;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Creator {

    private static final Random random = new Random();
    private final Class<?> interfaze;

    public Creator(Class<?> clazz) {
        this.interfaze = clazz;
    }

    public Object apply(Object... args) {
        Class[] argTypes = Invoker.getArgTypes(args);

        String packageName = interfaze.getPackage().getName();
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> allAnnotated = reflections.getTypesAnnotatedWith(Redundancy.class);

        List<Class<?>> implementations = new ArrayList<Class<?>>();
        for (Class<?> clazz : allAnnotated) {
            Redundancy redundancy = clazz.getAnnotation(Redundancy.class);
            if (redundancy.value().equals(interfaze)) {
                implementations.add(clazz);
            }
        }

        int i = random.nextInt(implementations.size());
        try {
            Constructor<?> constructor = implementations.get(i).getConstructor(argTypes);
            return constructor.newInstance(args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
