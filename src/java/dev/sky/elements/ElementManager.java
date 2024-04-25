package dev.sky.elements;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.elements.impls.*;
import dev.sky.elements.impls.keystore.KeyStore;
import dev.sky.events.impls.render.EventRender2D;
import dev.sky.module.Module;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author LangYa466
 * @date 2024/4/11 18:32
 */

@Getter
public class ElementManager {
    List<Element> elements;

    public ElementManager() {
        elements = new CopyOnWriteArrayList<>();
        Client.INSTANCE.getEventManager().register(this);
        this.registers();
    }

    public Element getElement(String name) {
        for (Element element : elements) if(element.getName().equalsIgnoreCase(name)) return element;
        return null;
    }

    void registers() {
        elements.add(new Info());
        elements.add(new TargetHUD());
        elements.add(new KeyStore());
        elements.add(new ComboInfo());
        elements.add(new PotionInfo());
    }

    @EventTarget
    void onR2D(EventRender2D e) {
        for (Element element : elements) {
            if (!element.isState()) continue; element.update();
        }
    }

}
