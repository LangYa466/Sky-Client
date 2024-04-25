package dev.sky.utils.impls.player;

import dev.sky.utils.IMinecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.io.File;

public class ChatUtil extends IMinecraft {

    public static void print(boolean prefix, String message) {
        if (mc.thePlayer != null) {
            message = prefix ? "§7[SkyClient§7] {" + message + "}" : message;
            mc.thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }

    public static void error(String message) {
        if (mc.thePlayer != null) {
            mc.thePlayer.addChatMessage(new ChatComponentText("§7[§c§Error§r§7] {" + message + "}"));
        }
    }

    public static void scriptError(File scriptFile, String message) {
        if (mc.thePlayer != null) {
            mc.thePlayer.addChatMessage(new ChatComponentText("§d§lScriptError: §r§f{" + scriptFile.getName() + "} §c{" + message + "}"));
        }
    }

    public static void print(String prefix, EnumChatFormatting color, String message) {
        if (mc.thePlayer != null) {
            message = "§7[§" + color.formattingCode + "§l" + prefix.toUpperCase() + "§r§7]§r §" + color.formattingCode + message;
            mc.thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }

    public static void print(Object o) {
        print(true, String.valueOf(o));
    }

    public static void send(String message) {
        if (mc.thePlayer != null) {
            mc.thePlayer.sendChatMessage(message);
        }
    }
}
