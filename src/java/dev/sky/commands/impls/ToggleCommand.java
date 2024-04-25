package dev.sky.commands.impls;


import dev.sky.Client;
import dev.sky.commands.Command;
import dev.sky.module.Module;
import dev.sky.utils.impls.player.ChatUtil;

/**
 * @author LangYa466
 * @date 2024/4/10 17:10
 */

public class ToggleCommand implements Command {

    @Override
    public boolean run(String[] args) {
        if (args[1].isEmpty()) return false;
        Module module = Client.INSTANCE.getModuleManager().getModule(args[1]);
        if (module == null) {
            ChatUtil.print("没有这个模块");
            return false;
        }

        module.toggle();
        ChatUtil.print(module.isState() ? "§a已开启 §7" : "§c已关闭 §7" + module.getName() + "§7");

        return true;
    }

    @Override
    public String usage() {
        return ".t <模块名称>";
    }
}
