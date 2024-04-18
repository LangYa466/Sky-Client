package dev.sky.elements.impls;

import dev.sky.elements.Element;
import dev.sky.manager.TargetManager;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.animations.AnimationUtil;
import dev.sky.utils.render.GLUtil;
import dev.sky.utils.render.RoundedUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import org.lwjgl.opengl.GL11C;

import java.awt.*;


/**
 * @author LangYa466
 * @date 2024/4/18 22:02
 */

public class TargetHUD extends Element {

    public TargetHUD() {
        super(250, 250);
        setState(true);
    }
    int width,height;

    @Override
    public void draw() {
        Entity target = TargetManager.INSTANCE.getTarget();

        if(target == null) return;

        if(!(target instanceof EntityMob)) return;

        // draw
        FontManager.pingfang25.drawStringWithShadow(target.getDisplayName().getFormattedText(),getX() + 20,getY() - 5,-1);
        FontManager.pingfang20.drawStringWithShadow(String.valueOf(((EntityLiving)target).getHealth()),getX() + 30,getY() - 20,Color.black.getRGB());
        RoundedUtil.drawRound(getX(),getY(),width,50,5,new Color(0,0,0,80));
        RoundedUtil.drawRound(getX() + 10,getY() - 20,AnimationUtil.animate(((EntityLiving)target).getHealth(),((EntityLiving) target).getMaxHealth(),2),50,5,new Color(231, 61, 61));
        width = FontManager.pingfang25.getStringWidth(target.getDisplayName().getFormattedText()) + 30;
        height = 50;

        this.setWidth(width);
        this.setHeight(height);
    }
}
