package dev.sky.ui.screen.button;

import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.misc.MouseUtil;
import dev.sky.utils.impls.render.RoundedUtil;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Keyboard;

import java.awt.*;

/**
 * @author LangYa466
 * @date 2024/4/19 21:42
 */

@Getter
@Setter
public class ClientButton {


    float x,y,width,height,radius;
    int mouseX,mouseY;
    boolean hover;
    String text;

    public ClientButton(float x, float y, float width, float height,String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = 0;
        this.text = text;
    }
    
    public ClientButton(float x, float y, float width, float height, float radius ,String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.text = text;
    }
    

    public void draw() {
        hover = MouseUtil.isHovering(x, y, width, height, mouseX, mouseY);

        if(hover) RoundedUtil.drawRound(x,y,width,height,radius,new Color(0,0,0,160));
        else RoundedUtil.drawRound(x,y,width,height,radius,new Color(0,0,0,80));

        FontManager.pingfang30.drawStringWithShadow(text,x + 12,y + 10,-1);
    }

}
