package dev.sky.elements.impls;

import dev.sky.elements.Element;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.animations.AnimationUtil;
import dev.sky.utils.impls.render.RenderUtil;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11C;

import java.awt.*;


/**
 * @author LangYa466
 * @date 2024/4/18 22:02
 */

public class TargetHUD extends Element {

    public TargetHUD() {
        super(50, 50);
        setState(true);
    }
    int width,height;

    @Override
    public void draw() {
        AbstractClientPlayer target = mc.thePlayer;

        if(mc.theWorld == null || mc.thePlayer == null) return;



        if(target.hurtTime <= 0) return;


        width = (int) (FontManager.pingfang25.getStringWidth(target.getName()) + target.getMaxHealth() * 5);
        height = 50;

        this.setWidth(width);
        this.setHeight(height);


        GlStateManager.popMatrix();
        GL11C.glPointSize(0.8F);

        // draw
        RoundedUtil.drawRound(getX(),getY(),width,50,5,new Color(0,0,0,80));
        RoundedUtil.drawRound(getX() + 50,getY() + 35, AnimationUtil.animate( target.getHealth() * 5, target.getHealth() * 5,0.2),10,5,new Color(231, 61, 61));
        RenderUtil.drawBigHead(getX() + 2,getY() + 2, 45,45, target);


        FontManager.pingfang25.drawStringWithShadow(target.getDisplayName().getFormattedText(),getX() + 50,getY() + 5,-1);
        FontManager.pingfang20.drawStringWithShadow(String.valueOf(((int) target.getHealth())),getX() + 50,getY() + 35,-1);

        GlStateManager.pushMatrix();

    }
}
