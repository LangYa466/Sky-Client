package dev.sky.elements.impls;

import dev.sky.elements.Element;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.client.Minecraft;

import java.awt.*;

/**
 * @author LangYa466
 * @date 2024/4/11 19:05
 */

public class Info extends Element {

    public Info() {
        super(50, 50);
        this.setState(true);
    }

    @Override
    public void draw() {
        double bps = Math.hypot(
                mc.thePlayer.posX - mc.thePlayer.prevPosX,
                mc.thePlayer.posZ - mc.thePlayer.prevPosZ
            ) * mc.timer.timerSpeed * 20;
        String str = String.format("SkyClient | FPS: %s | BPS: %s", Minecraft.getDebugFPS(), Math.round(bps * 100.0) / 100.0);

        // draw
        RoundedUtil.drawRound(getX() - 1,getY(),getWidth(),getHeight(),2,new Color(0,0,0,100));
        FontManager.pingfang18.drawStringWithShadow(
                str,
                getX(),
                getY(),
                -1
        );

        this.setWidth(FontManager.pingfang18.getStringWidth(str) + 6);
        this.setHeight(FontManager.pingfang18.getHeight());
    }

}
