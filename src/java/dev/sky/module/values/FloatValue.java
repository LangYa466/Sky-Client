package dev.sky.module.values;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FloatValue extends AbstractValue<Float> {
    String name;
    float number;
    float maximum;
    float minimum;
    public FloatValue(String name, float defaultValue, float maximum, float minimum) {
        super(name);
        this.name = name;
        this.number = defaultValue;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public Float get() {
        return this.number;
    }

    @Override
    public void set(Float value) {
        this.number = value;
    }

}
