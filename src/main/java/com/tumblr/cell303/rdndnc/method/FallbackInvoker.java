package com.tumblr.cell303.rdndnc.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FallbackInvoker extends Invoker {

    public FallbackInvoker(Object base) {
        super(base);
    }

    @Override
    protected Object invoke(Object base, String methodName, List<Method> methods, Object[] args) throws IllegalAccessException {
        for (Method method : methods) {
            try {
                return method.invoke(base, args);
            } catch (InvocationTargetException ignored) {
            }
        }

        throw new RuntimeException("No method applicable");
    }
}
