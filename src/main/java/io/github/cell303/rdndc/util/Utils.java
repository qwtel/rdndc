package io.github.cell303.rdndc.util;

public class Utils {

    public static Class[] getParameterTypes(Object[] args) {
        Class[] argTypes = new Class[args.length];
        int i = 0;
        for (Object o : args) {
            argTypes[i++] = o.getClass();
        }
        return argTypes;
    }
}
