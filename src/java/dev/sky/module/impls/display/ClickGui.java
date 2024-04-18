package dev.sky.module.impls.display;

import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.ui.clickgui.ClickGUI;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {
    public ClickGui() {
        super("ClickGui","clickable gui", Category.Render,
        Keyboard.KEY_RSHIFT);
    }

    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
    }
}
