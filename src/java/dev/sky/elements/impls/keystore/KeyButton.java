package dev.sky.elements.impls.keystore;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.events.impls.misc.EventKey;
import dev.sky.events.impls.misc.EventTick;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * @author LangYa466
 * @date 2024/4/19 20:30
 */

public class KeyButton {

    float x,y,width,height,radius;
    int keyCode;

    public KeyButton(float x, float y, float width, float height, float radius, int keyCode) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.keyCode = keyCode;
        draw();
        Client.INSTANCE.getEventManager().register(this);
    }

    @EventTarget
    void onK(EventKey e) {
        keyCode = e.getKeyCode();
    }

    public void draw() {


        if(Keyboard.getEventKey() == keyCode) RoundedUtil.drawRound(x,y,width,height,radius,new Color(0,0,0,160));
        else RoundedUtil.drawRound(x,y,width,height,radius,new Color(0,0,0,80));

        FontManager.pingfang15.drawStringWithShadow(Keyboard.getKeyName(keyCode),x + 12,y + 10,-1);

    }

}
