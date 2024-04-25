package dev.sky.elements.impls.keystore;

import dev.sky.elements.Element;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;

/**
 * @author LangYa466
 * @date 2024/4/19 20:29
 */

public class KeyStore extends Element {
    public KeyStore() {
        super("按键显示",80,80);
        setState(true);
    }

    @Override
    public void draw() {
        setWidth(90);
        setHeight(90);

        GlStateManager.pushMatrix();
        GlStateManager.translate(30,0,0);
        new KeyButton(getX(),getY(),30,30,3, Keyboard.KEY_W);
        new KeyButton(getX() - 30,getY() + 30,30,30,3, Keyboard.KEY_A);
        new KeyButton(getX(),getY() + 30,30,30,3, Keyboard.KEY_S);
        new KeyButton(getX() + 30,getY() + 30,30,30,3, Keyboard.KEY_D);
        new KeyButton(getX() - 30,getY() + 60, 30 * 3,30,3, Keyboard.KEY_SPACE);
        GlStateManager.popMatrix();

    }
}
