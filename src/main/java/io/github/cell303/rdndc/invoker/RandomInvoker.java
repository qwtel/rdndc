package io.github.cell303.rdndc.invoker;

import io.github.cell303.rdndc.util.RedundancyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RandomInvoker extends Invoker {
    public static final Random random = new Random();
    private static final Logger LOGGER = Logger.getLogger(RandomInvoker.class.getName());

    public RandomInvoker(Object base) {
        super(base);
    }

    @Override
    protected Object invoke(Object base, Signature signature, List<Method> methods, Object[] args) {
        String methodName = signature.getName();
        if (methods.size() == 0) {
            throw new RedundancyException("0 implementations of method " + methodName);
        }

        int i = random.nextInt(methods.size());
        Method method = null;
        try {
            method = methods.get(i);
            return method.invoke(base, args);
        } catch (IllegalAccessException e) {
            throw new RedundancyException("No access to method " + method.getName(), e);
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Exception while invoking " + method.getName() + ". Falling back...", e.getCause());
            return new FallbackInvoker(base).invoke(methodName, args);
        }
    }
}
