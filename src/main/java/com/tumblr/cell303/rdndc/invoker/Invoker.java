package com.tumblr.cell303.rdndc.invoker;

import com.google.common.collect.ImmutableMap;
import com.tumblr.cell303.rdndc.util.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Invoker {

    private static final Map<Class<?>, Class<?>> PRIMITIVES_TO_WRAPPERS = new ImmutableMap.Builder<Class<?>, Class<?>>()
            .put(boolean.class, Boolean.class)
            .put(byte.class, Byte.class)
            .put(char.class, Character.class)
            .put(double.class, Double.class)
            .put(float.class, Float.class)
            .put(int.class, Integer.class)
            .put(long.class, Long.class)
            .put(short.class, Short.class)
            .put(void.class, Void.class)
            .build();

    private final Object base;
    private final Map<Signature, List<Method>> cache = new ConcurrentHashMap<Signature, List<Method>>(0, 0.9f, 1);

    public Invoker(Object base) {
        this.base = base;
    }

    private static List<Method> getAnnotatedMethods(Object base, String method, Class[] argTypes) {
        List<Method> methods = new ArrayList<Method>();
        for (Method m : base.getClass().getMethods()) {
            rdndc a = m.getAnnotation(rdndc.class);
            if (a != null && a.value().equals(method) && checkParameterTypes(m, argTypes)) {
                methods.add(m);
            }
        }
        return methods;
    }

    private static boolean checkParameterTypes(Method m, Class[] argTypes) {
        if (argTypes.length == m.getParameterTypes().length) {
            int i = 0;
            for (Class clazz : m.getParameterTypes()) {
                if (!wrap(clazz).equals(argTypes[i++])) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }


    /**
     * http://stackoverflow.com/a/1704658
     */
    @SuppressWarnings("unchecked") // safe because both Long.class and long.class are of type Class<Long>
    private static <T> Class<T> wrap(Class<T> c) {
        return c.isPrimitive() ? (Class<T>) PRIMITIVES_TO_WRAPPERS.get(c) : c;
    }

    public Object invoke(String methodName, Object... args) {
        Class[] argTypes = Utils.getParameterTypes(args);
        Signature s = new Signature(methodName, Arrays.asList(argTypes));

        List<Method> methods = cache.get(s);
        if (methods == null) {
            methods = getAnnotatedMethods(base, methodName, argTypes);
            cache.put(s, methods);
        }

        return invoke(base, s, methods, args);
    }

    protected abstract Object invoke(Object base, Signature signature, List<Method> methods, Object[] args);
}
