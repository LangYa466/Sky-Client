package dev.sky.commands;


import dev.sky.Client;
import dev.sky.commands.impls.BindCommand;
import dev.sky.commands.impls.ToggleCommand;
import dev.sky.utils.impls.player.ChatUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LangYa466
 * @date 2024/4/10 13:03
 */

@Getter
@Setter
public class CommandManager {

    HashMap<String[], Command> commands;

    public CommandManager() {
        commands = new HashMap<>();
        Client.INSTANCE.getEventManager().register(this);
        this.registerCommands();
    }

    void registerCommands() {
        commands.put(new String[]{"bind", "b"}, new BindCommand());
        commands.put(new String[]{"toggle", "t"}, new ToggleCommand());
    }

    Command getCommand(String name) {
        return commands.entrySet().stream()
                .filter(entry -> Arrays.stream(entry.getKey()).anyMatch(s -> s.equalsIgnoreCase(name)))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public boolean check(String message) {
        if (message.length() <= 1 && message.startsWith(".")) {
            ChatUtil.print(".help");
            return true;
        } else if (message.startsWith(".")) {

            String[] args = message.substring(1).split(" ");
            Command command = this.getCommand(args[0]);

            if (command != null) command.run(args); else ChatUtil.print(".help");

            return true;
        }
        return false;
    }
}
