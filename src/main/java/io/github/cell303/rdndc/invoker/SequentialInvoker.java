package io.github.cell303.rdndc.invoker;

import io.github.cell303.rdndc.util.RedundancyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SequentialInvoker extends Invoker {
    private static final Logger LOGGER = Logger.getLogger(SequentialInvoker.class.getSimpleName());

    private Map<Signature, AtomicInteger> is = new ConcurrentHashMap<Signature, AtomicInteger>(0, 0.9f, 1);

    public SequentialInvoker(Object base) {
        super(base);
    }

    @Override
    protected Object invoke(Object base, Signature signature, List<Method> methods, Object[] args) {
        String methodName = signature.getName();
        if (methods.size() == 0) {
            throw new RedundancyException("0 implementations of method " + methodName);
        }

        AtomicInteger i = getIndex(signature);

        Method method = null;
        try {
            int j = i.getAndIncrement();
            method = methods.get(j % methods.size());
            return method.invoke(base, args);
        } catch (IllegalAccessException e) {
            throw new RedundancyException("No access to method " + method.getName(), e);
        } catch (InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Exception while invoking " + method.getName() + ". Falling back...", e.getCause());
            return new FallbackInvoker(base).invoke(methodName, args);
        }
    }

    private AtomicInteger getIndex(Signature signature) {
        AtomicInteger j = is.get(signature);
        if (j == null) {
            j = new AtomicInteger(0);
            is.put(signature, j);
        }
        return j;
    }
}
