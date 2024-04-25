package dev.sky.module.impls.render;

import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.ui.clickgui.ClickGUI;
import org.lwjgl.input.Keyboard;

public class ClickGui extends Module {
    public ClickGui() {
        super("模块管理页面","点击管理你的模块", Category.Render,
        Keyboard.KEY_RSHIFT);
    }

    public void onEnable() {
        mc.displayGuiScreen(new ClickGUI());
    }
}
