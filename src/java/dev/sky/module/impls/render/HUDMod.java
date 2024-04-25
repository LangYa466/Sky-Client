package dev.sky.module.impls.render;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.elements.Element;
import dev.sky.elements.impls.notification.Notification;
import dev.sky.events.impls.render.EventRender2D;
import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.module.values.AbstractValue;
import dev.sky.module.values.BoolValue;
import net.minecraft.client.gui.ScaledResolution;

public class HUDMod extends Module {
    public HUDMod() {
        super("视觉管理","自定义你的视觉", Category.Render);
        addElementsSettings();
        addValues();
    }

    void addElementsSettings() {
        for (Element element : Client.INSTANCE.getElementManager().getElements())
            values.add(new BoolValue(element.getName(),true));
    }

    @EventTarget
    public void onRender2DEvent(EventRender2D e){

        // draw notification
        ScaledResolution sr = new ScaledResolution(mc);
        double startY = sr.getScaledHeight() - 25;
        double lastY = startY;

        for (int i = 0; i < Client.INSTANCE.getNotificationManager().getNotifications().size(); i++) {
            Notification not = Client.INSTANCE.getNotificationManager().getNotifications().get(i);
            if (not.shouldDelete()) Client.INSTANCE.getNotificationManager().getNotifications().remove(i);
            not.draw(startY, lastY);
            startY -= not.getHeight() + 1;
        }

        for (AbstractValue value : values) {
            if (value instanceof BoolValue)
                Client.INSTANCE.getElementManager().getElement(((BoolValue) value).getName()).setState(((BoolValue) value).isState());
        }
    }
}
