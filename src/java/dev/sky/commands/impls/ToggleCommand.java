package dev.sky.commands.impls;


import dev.sky.Client;
import dev.sky.commands.Command;
import dev.sky.module.Module;
import dev.sky.utils.player.ChatUtil;

/**
 * @author LangYa466
 * @date 2024/4/10 17:10
 */

public class ToggleCommand implements Command {


    @Override
    public boolean run(String[] args) {
        Module module = Client.INSTANCE.getModuleManager().getModule(args[1]);
        if (module == null) {
            ChatUtil.print("No module found");
            return false;
        }
        if (module.isState()){
            module.toggle();
            ChatUtil.print("&cDisabler &7" + module.getName() + "&7");
        }else{
            module.toggle();
            ChatUtil.print("&aEnable &7" + module.getName() + "&7");
        }



        return true;
    }

    @Override
    public String usage() {
        return ".t <Module>";
    }
}
