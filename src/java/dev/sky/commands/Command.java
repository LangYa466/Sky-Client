package dev.sky.commands;

/**
 * @author LangYa466
 * @date 2024/4/10 12:45
 */

public interface Command {
    boolean run(String[] args);

    String usage();
}