package net.minecraft.client.gui;

import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class GuiButtonLanguage extends GuiButton {
    public GuiButtonLanguage(int buttonID, int xPos, int yPos) {
        super(buttonID, xPos, yPos, 20, 20, "");
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            if (flag)
                RoundedUtil.drawRound(this.xPosition, this.yPosition, this.width, this.height, 3, new Color(0, 0, 0, 160));
            else
                RoundedUtil.drawRound(this.xPosition, this.yPosition, this.width, this.height, 3, new Color(0, 0, 0, 80));

            FontManager.pingfang15.drawCenteredStringWithShadow("语言", this.xPosition + this.width / 1.8, this.yPosition + this.height / 3.0, new Color(255, 255, 255, 255).getRGB());
        }
    }
}
