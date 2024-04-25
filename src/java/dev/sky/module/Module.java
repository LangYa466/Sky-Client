package dev.sky.module;


import dev.sky.Client;
import dev.sky.elements.impls.notification.NotifyType;
import dev.sky.module.values.AbstractValue;
import dev.sky.utils.IMinecraft;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class Module extends IMinecraft {
    public CopyOnWriteArrayList<AbstractValue> values = new CopyOnWriteArrayList<>();
    String name, description;
    Category category;
    int keyCode;
    boolean state;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Module(String name, String description, Category category, int keyCode) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyCode = keyCode;
    }

    public Module(String name, Category category) {
        this.name = name;
        this.description = "";
        this.category = category;
    }

    public Module(String name, Category category, int keyCode) {
        this.name = name;
        this.description = "";
        this.category = category;
        this.keyCode = keyCode;
    }

    protected void addValues() {
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object fieldValue = field.get(this);

                if (fieldValue instanceof AbstractValue) {
                    values.add((AbstractValue) fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void toggle() {
        state = !state;
        if(state) onEnable(); else onDisable();
        Client.INSTANCE.getNotificationManager().addNotification(state ? "已开启" : "已关闭" + name, state ? NotifyType.SUCCESS : NotifyType.ERROR);
    }

    public void onEnable() {
        Client.INSTANCE.getEventManager().register(this);
    }
    public void onDisable() {
        Client.INSTANCE.getEventManager().unregister(this);
    }

}
