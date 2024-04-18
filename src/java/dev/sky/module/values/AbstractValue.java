package dev.sky.module.values;

public abstract class AbstractValue<V> {
    public String name;
    protected AbstractValue(String name) {
        this.name = name;
    }
    public abstract V get();
    public abstract void set(V value);
}
