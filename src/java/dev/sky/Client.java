package dev.sky;

import com.cubk.event.EventManager;
import dev.sky.commands.CommandManager;
import dev.sky.elements.ElementManager;
import dev.sky.manager.TargetManager;
import dev.sky.module.ModuleManager;
import dev.sky.ui.font.FontManager;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.viamcp.ViaMCP;

@SuppressWarnings("ALL")
@Getter
@Setter
public class Client {

    public static final Client INSTANCE = new Client();

    public static final String NAME = "SkyClient";
    public static final String VERSION = "0.0.1";

    ModuleManager moduleManager;
    CommandManager commandManager;
    EventManager eventManager;
    ElementManager elementManager;
    TargetManager targetManager;

    public void startClient() {
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        elementManager = new ElementManager();
        targetManager = new TargetManager();
        ViaMCP.getInstance().start();
        ViaMCP.getInstance().initAsyncSlider();
        FontManager.initFonts();
    }

    public void stopClient() { }
}


