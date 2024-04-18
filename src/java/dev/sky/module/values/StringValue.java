package dev.sky.module.values;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa
 * @date 2024/2/3 . 04:43
 */


@Getter
@Setter
public class StringValue extends AbstractValue {
    String name;
    String value;


    public StringValue(String name,String value) {
        super(name);
        this.name = name;
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public void set(Object value) {
        if(value.toString().isEmpty()) return;
        this.value = (String) value;
    }

}

