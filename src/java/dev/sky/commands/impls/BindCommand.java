package dev.sky.commands.impls;


import dev.sky.Client;
import dev.sky.commands.Command;
import dev.sky.module.Module;
import dev.sky.utils.player.ChatUtil;
import org.lwjgl.input.Keyboard;

/**
 * @author LangYa466
 * @date 2024/4/10 16:38
 */

public class BindCommand implements Command {

    @Override
    public boolean run(String[] args) {

        Module module = Client.INSTANCE.getModuleManager().getModule(args[1]);

        if (module == null) {
            ChatUtil.print("No module found");
            return false;
        }

        module.setKeyCode(Keyboard.getKeyIndex(args[2].toUpperCase()));
        ChatUtil.print( "&f" + module.getName()+ "&7" + " Set keybind for to &f" + args[2] + "&7");

        return true;
    }


    @Override
    public String usage() { return ".bind <Module> <Key>"; }
}