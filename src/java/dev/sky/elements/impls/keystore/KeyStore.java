package dev.sky.elements.impls.keystore;

import dev.sky.elements.Element;
import org.lwjgl.input.Keyboard;

/**
 * @author LangYa466
 * @date 2024/4/19 20:29
 */

public class KeyStore extends Element {
    public KeyStore() {
        super(80,80);
        setState(true);
    }

    @Override
    public void draw() {
        setWidth(30);
        setHeight(30);
        new KeyButton(getX(),getY(),getWidth(),getHeight(),3, Keyboard.KEY_W);
        new KeyButton(getX() - 30,getY() + 30,getWidth(),getHeight(),3, Keyboard.KEY_A);
        new KeyButton(getX(),getY() + 30,getWidth(),getHeight(),3, Keyboard.KEY_S);
        new KeyButton(getX() + 30,getY() + 30,getWidth(),getHeight(),3, Keyboard.KEY_D);
        new KeyButton(getX() - 30,getY() + 60, getWidth() * 3,getHeight(),3, Keyboard.KEY_SPACE);
    }
}
