package com.tumblr.cell303.rdndnc.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class RandomInvoker extends Invoker {

    public static Random random = new Random();

    public RandomInvoker(Object base) {
        super(base);
    }

    @Override
    protected Object invoke(Object base, String methodName, List<Method> methods, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        int i = random.nextInt(methods.size());
        try {
            return methods.get(i).invoke(base, args);
        } catch (InvocationTargetException e) {
            return new FallbackInvoker(base).apply(methodName, args);
        }
    }
}
