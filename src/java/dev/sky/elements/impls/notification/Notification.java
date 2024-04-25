package dev.sky.elements.impls.notification;

import java.awt.Color;

import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.misc.TimerUtil;
import dev.sky.utils.impls.render.ColorUtil;
import dev.sky.utils.impls.render.RenderUtil;
import dev.sky.utils.impls.render.RoundedUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

// https://github.com/LittleInk/eTb-f1X/blob/main/com/etb/client/gui/notification/Notification.java
@Getter
@Setter
public class Notification {
    private String message;
    private TimerUtil timer;
    private double lastY, posY, width, height, animationX;
    private Color color = new Color(38, 46, 52, 255);
    private int imageWidth;
    private ResourceLocation image;
    private long stayTime;

    public Notification(String message, NotifyType notifyType) {
        this.message = message;
        timer = new TimerUtil();
        timer.reset();
        width = FontManager.pingfang14.getStringWidth(message) + 35;
        height = 20;
        animationX = width;
        stayTime = 2000;
        imageWidth = 13;
        posY = -1;
        image = new ResourceLocation("skyclient/icons/" + notifyType.name().toLowerCase() + ".png");
    }

    public void draw(double getY, double lastY) {
        this.lastY = lastY;
        animationX = getAnimationState(animationX, isFinished() ? width : 0, Math.max(isFinished() ? 200 : 30, Math.abs(animationX - (isFinished() ? width : 0)) * 5));
        if(posY == -1)
            posY = getY;
        else
            posY = getAnimationState(posY, getY, 200);
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int x1 = (int) (res.getScaledWidth() - width + animationX), x2 = (int) (res.getScaledWidth() + animationX), y1 = (int) posY, y2 = (int) (y1 + height);
        RoundedUtil.drawRound(x1, y1, x2, y2,3, color);
        RoundedUtil.drawRound(x1, y2, x2, y2 + 0.5F,3, new Color(38, 46, 52, 255));
        RoundedUtil.drawRound(x1, y2, x2, y2 + 0.5F,3, new Color(26, 31, 41, 255));

        RoundedUtil.drawRound(x1, y1, (int) (x1 + height), y2, 3, new Color(ColorUtil.reAlpha(-1, 255)));
        RenderUtil.drawImage(image, (int)(x1 + (height - imageWidth) / 2.0F), y1 + (int)((height - imageWidth) / 2.0F), imageWidth, imageWidth);

        FontManager.pingfang14.drawCenteredString(message, (float)(x1 + width / 2.0F) + 10, (float)(y1 + height / 3.5F), -1);
    }

    private double getAnimationState(double animation, double finalState, double speed) {
        float add = (float) (0.01 * speed);
        if (animation < finalState) {
            if (animation + add < finalState)
                animation += add;
            else
                animation = finalState;
        } else {
            if (animation - add > finalState)
                animation -= add;
            else
                animation = finalState;
        }
        return animation;
    }

    public boolean shouldDelete() {
        return isFinished() && animationX >= width;
    }

    private boolean isFinished() {
        return timer.hasTimeElapsed(stayTime) && posY == lastY;
    }
}