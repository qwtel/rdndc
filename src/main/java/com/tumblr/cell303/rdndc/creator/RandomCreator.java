package com.tumblr.cell303.rdndc.creator;

import com.tumblr.cell303.rdndc.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

public class RandomCreator extends Creator {

    private static final Random random = new Random();

    public static Object create(Class<?> base, Object... args) {
        List<Class<?>> implementations = getImplementations(base);
        int i = random.nextInt(implementations.size());
        try {
            Class[] argTypes = Utils.getParameterTypes(args);
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
