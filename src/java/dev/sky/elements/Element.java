package dev.sky.elements;

import dev.sky.ui.font.FontManager;
import dev.sky.utils.IMinecraft;
import dev.sky.utils.impls.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa466
 * @date 2024/4/11 18:16
 */


@Getter
@Setter
public class Element extends IMinecraft {
    float mouseX, mouseY;
    float x, y, moveX, moveY, width, height;
    boolean state, dragging;

    public Element(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (dragging) {
            this.setX(this.mouseX - this.moveX);
            this.setY(this.mouseY - this.moveY);
        }

        this.draw();
    }

    public void draw() { }

    void toggle() { state = !state; }

    public void updateMousePos(int mouseX, int mouseY) {
        if (dragging) {
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            RenderUtil.drawBorder(x - 4,y - 4,width + 8,height + 8,1,-1);
            FontManager.pingfang15.drawCenteredStringWithShadow(String.format("PosX: %s PosY: %s",x,y,mouseX,mouseY,width,height),x,y - 12,-1);
        }
    }
}


