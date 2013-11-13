package com.tumblr.cell303.rdndc.invoker;

import com.tumblr.cell303.rdndc.util.RedundancyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FallbackInvoker extends Invoker {
    private static final Logger LOGGER = Logger.getLogger(FallbackInvoker.class.getName());

    public FallbackInvoker(Object base) {
        super(base);
    }

    @Override
    protected Object invoke(Object base, Signature signature, List<Method> methods, Object[] args) {
        String methodName = signature.getName();
        for (Method method : methods) {
            try {
                return method.invoke(base, args);
            } catch (InvocationTargetException ignored) {
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.WARNING, "No access to method " + method.getName());
            }
        }

        throw new RedundancyException("No method applicable for " + methodName);
    }
}
