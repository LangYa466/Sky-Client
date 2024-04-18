package dev.sky.module.values;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoolValue extends AbstractValue<Boolean> {
    boolean state;
    String name;

    public BoolValue(String name, boolean state) {
        super(name);
        this.name = name;
        this.state = state;
    }
    
    @Override
    public void set(Boolean value) {
        this.state = value;
    }

    @Override
    public Boolean get() {
        return state;
    }
}
