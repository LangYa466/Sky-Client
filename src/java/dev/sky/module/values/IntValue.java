package dev.sky.module.values;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntValue extends AbstractValue<Integer> {
   String name;
   Integer number;
   int maximum;
   int minimum;
    public IntValue(String name, int defaultValue, int maximum, int minimum) {
        super(name);
        this.name = name;
        this.number = defaultValue;
        this.maximum = maximum;
        this.minimum = minimum;
    }
    @Override
    public Integer get() {
        return this.number;
    }

    @Override
    public void set(Integer value) {
        this.number = value;
    }

}