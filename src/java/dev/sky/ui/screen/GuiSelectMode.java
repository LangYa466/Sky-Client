package dev.sky.ui.screen;

import dev.sky.ui.screen.button.ClientButton;
import net.minecraft.client.gui.GuiScreen;

import javax.swing.*;
import java.io.IOException;

/**
 * @author LangYa466
 * @date 2024/4/19 21:42
 */

public class GuiSelectMode extends GuiScreen {

    ClientButton legitButton = new ClientButton(100, 100, 100, 100,3, "Legit");
    ClientButton hackButton = new ClientButton(200, 200, 100, 100,3, "Hack");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawWorldBackground(0);
        legitButton.draw();
        hackButton.draw();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        legitButton.setMouseX(mouseX);
        legitButton.setMouseY(mouseY);

        hackButton.setMouseX(mouseX);
        hackButton.setMouseY(mouseY);
        if(mouseButton == 0) {
            if(legitButton.isHover()) {
                JOptionPane.showInputDialog("sb");
            }
            if(hackButton.isHover()) {
                JOptionPane.showInputDialog("hack");
            }
        }
    }
}
