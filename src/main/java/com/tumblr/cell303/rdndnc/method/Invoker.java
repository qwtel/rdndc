package com.tumblr.cell303.rdndnc.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Invoker {

    private final static Map<Signature, List<Method>> cache = new ConcurrentHashMap<Signature, List<Method>>(0, 0.9f, Runtime.getRuntime().availableProcessors());

    private final Object base;

    public Invoker(Object base) {
        this.base = base;
    }

    public Object apply(String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        Class[] argTypes = getArgTypes(args);
        Signature s = new Signature(methodName, Arrays.asList(argTypes));

        List<Method> methods = cache.get(s);
        if (methods == null) {
            methods = getRedundantMethods(base, methodName, argTypes);
            cache.put(s, methods);
        }

        return invoke(base, methodName, methods, args);
    }

    protected abstract Object invoke(Object base, String methodName, List<Method> methods, Object[] args) throws InvocationTargetException, IllegalAccessException;

    public static Class[] getArgTypes(Object[] args) {
        Class[] argTypes = new Class[args.length];
        int i = 0;
        for (Object o : args) {
            argTypes[i++] = o.getClass();
        }
        return argTypes;
    }

    public static List<Method> getRedundantMethods(Object base, String method, Class[] argTypes) {
        List<Method> methods = new ArrayList<Method>();
        for (Method m : base.getClass().getMethods()) {
            rdndnc a = m.getAnnotation(rdndnc.class);
            if (a != null && a.value().equals(method)) {
                if (haveEqualArguments(argTypes, m)) {
                    methods.add(m);
                }
            }
        }
        return methods;
    }

    public static boolean haveEqualArguments(Class[] argTypes, Method m) {
        if (argTypes.length == m.getParameterTypes().length) {
            int i = 0;
            for (Class clazz : m.getParameterTypes()) {
                if (!clazz.equals(argTypes[i++])) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
