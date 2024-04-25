package dev.sky;

import com.cubk.event.EventManager;
import dev.sky.commands.CommandManager;
import dev.sky.elements.ElementManager;
import dev.sky.elements.impls.notification.NotificationManager;
import dev.sky.manager.TargetManager;
import dev.sky.module.ModuleManager;
import dev.sky.ui.font.FontManager;
import dev.sky.ui.screen.GuiSelectMode;
import dev.sky.utils.IMinecraft;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.viamcp.ViaMCP;

@SuppressWarnings("ALL")
@Getter
@Setter
public class Client extends IMinecraft {

    public static final Client INSTANCE = new Client();

    public static final String NAME = "SkyClient";
    public static final String VERSION = "0.0.2";

    EventManager eventManager;
    ElementManager elementManager;
    ModuleManager moduleManager;
    CommandManager commandManager;
    TargetManager targetManager;
    NotificationManager notificationManager;

    public void startClient() {
        eventManager = new EventManager();
        elementManager = new ElementManager();
        moduleManager = new ModuleManager();
        commandManager = new CommandManager();
        targetManager = new TargetManager();
        notificationManager = new NotificationManager();
        ViaMCP.getInstance().start();
        ViaMCP.getInstance().initAsyncSlider();
        FontManager.initFonts();
        // mc.displayGuiScreen(new GuiSelectMode());
    }

    public void stopClient() { }
}


