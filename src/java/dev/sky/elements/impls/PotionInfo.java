package dev.sky.elements.impls;

import dev.sky.elements.Element;
import dev.sky.ui.font.FontManager;
import dev.sky.utils.impls.render.RoundedUtil;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author LangYa466
 * @date 2024/4/21 22:04
 */

public class PotionInfo extends Element {

    List<Item> potions = new CopyOnWriteArrayList<>();

    public PotionInfo() {
        super("药水数量显示",300, 300);
        setState(true);
    }

    @Override
    public void draw() {
        if(mc.thePlayer == null) return;

        for (ItemStack item : mc.thePlayer.inventory.mainInventory) {
            if (item != null && item.getItem() instanceof ItemPotion) potions.add(item.getItem());
        }

        potions.removeIf(item -> !mc.thePlayer.inventory.mainInventory.equals(item));

        String str = "Potion:";

        if (!potions.isEmpty()) str = "Potions:";

        setWidth(FontManager.pingfang25.getStringWidth(str + potions.size()));
        setHeight(FontManager.pingfang25.getHeight());

        // draw
        RoundedUtil.drawRound(getX(), getY(), getWidth(), getHeight(), 3, new Color(0, 0, 0, 80));
        FontManager.pingfang25.drawStringWithShadow(str + potions.size(), getX() + 0.2, getY() + 0.2, -1);

    }
}
