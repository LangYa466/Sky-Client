package dev.sky.elements.impls;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.elements.Element;
import dev.sky.events.impls.player.EventAttack;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.entity.Entity;

import java.awt.*;

/**
 * @author LangYa466
 * @date 2024/4/21 22:08
 */

public class ComboInfo extends Element {

    Entity tempAttackEntity;
    int combo;

    public ComboInfo() {
        super("连击显示",500, 500);
        setState(true);
        Client.INSTANCE.getEventManager().register(this);
    }

    @EventTarget
    void onA(EventAttack e) {
        if (tempAttackEntity == null) tempAttackEntity = e.getEntity();

        new Thread(() -> {

            if (tempAttackEntity != null && mc.thePlayer.hurtTime < 1 && tempAttackEntity == e.getEntity()) {
                combo += 1;

                // 考虑到tick
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ignored) {}

            } else if (tempAttackEntity != null && tempAttackEntity != e.getEntity()) {
                combo = 0;
                tempAttackEntity = e.getEntity();
            }
        }).start();


    }

    @Override
    public void draw() {
        if (mc.thePlayer == null) return;

        if (combo > 0 && mc.thePlayer.hurtTime > 0) combo = 0;

        setWidth(FontManager.pingfang25.getStringWidth("Combo:" + combo));
        setHeight(FontManager.pingfang25.getHeight());

        // draw
        RoundedUtil.drawRound(getX(), getY(), getWidth(), getHeight(), 3, new Color(0, 0, 0, 80));
        FontManager.pingfang25.drawStringWithShadow("Combo:" + combo, getX() + 0.2, getY() + 0.2, -1);
    }
}
