package com.tumblr.cell303.rdndnc.method;

import java.util.List;

class Signature {
    public String name;
    public List<Class> argTypes;

    public Signature(String name, List<Class> argTypes) {
        this.name = name;
        this.argTypes = argTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Signature signature = (Signature) o;

        if (argTypes != null ? !argTypes.equals(signature.argTypes) : signature.argTypes != null) return false;
        if (name != null ? !name.equals(signature.name) : signature.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (argTypes != null ? argTypes.hashCode() : 0);
        return result;
    }
}

