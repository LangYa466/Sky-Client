package dev.sky.ui.font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;

import java.awt.*;

public class FontManager {

    public static FontDrawer pingfang14;
    public static FontDrawer pingfang15;
    public static FontDrawer pingfang16;
    public static FontDrawer pingfang18;
    public static FontDrawer pingfang20;
    public static FontDrawer pingfang25;
    public static FontDrawer pingfang30;
    public static FontDrawer pingfang50;

    public static FontDrawer pfBold15;
    public static FontDrawer pfBold20;
    public static FontDrawer pfBold25;
    public static FontDrawer pfBold30;

    public static FontDrawer cal15;
    public static FontDrawer cal18;
    public static FontDrawer cal20;
    public static FontDrawer cal25;
    public static FontDrawer cal30;
    public static FontDrawer cal50;

    public static FontDrawer calBold15;
    public static FontDrawer calBold20;
    public static FontDrawer calBold25;
    public static FontDrawer calBold30;

    public static void initFonts() {
        LogManager.getLogger().info("Loading FontManager..");

        pingfang14 = getFont("pingfang.ttf",14,true);
        pingfang15 = getFont("pingfang.ttf", 15, true);
        pingfang16 = getFont("pingfang.ttf",16,true);
        pingfang18 = getFont("pingfang.ttf", 18, true);
        pingfang20 = getFont("pingfang.ttf", 20, true);
        pingfang25 = getFont("pingfang.ttf", 25, true);
        pingfang30 = getFont("pingfang.ttf", 30, true);
        pingfang50 = getFont("pingfang.ttf",50,true);

        pfBold15 = getFont("pingfang-bold.ttf", 15, true);
        pfBold20 = getFont("pingfang-bold.ttf", 20, true);
        pfBold25 = getFont("pingfang-bold.ttf", 25, true);
        pfBold30 = getFont("pingfang-bold.ttf", 30, true);

        cal15 = getFont("CAL.ttf", 15, true);
        cal18 = getFont("CAL.ttf", 18, true);
        cal20 = getFont("CAL.ttf", 20, true);
        cal25 = getFont("CAL.ttf", 25, true);
        cal30 = getFont("CAL.ttf", 30, true);
        cal50 = getFont("CAL.ttf",50,true);

        pfBold15 = getFont("CAL-bold.ttf", 15, true);
        pfBold20 = getFont("CAL-bold.ttf", 20, true);
        pfBold25 = getFont("CAL-bold.ttf", 25, true);
        pfBold30 = getFont("CAL-bold.ttf", 30, true);

    }

    public static FontDrawer getFont(String name, int size, boolean antiAliasing) {
        Font font;
        try {
            font = Font.createFont(0, Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("skyclient/fonts/" + name)).getInputStream()).deriveFont(Font.PLAIN, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, size);
        }
        return new FontDrawer(font, antiAliasing,true);
    }
}
