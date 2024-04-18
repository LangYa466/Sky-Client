package dev.sky.module.impls.player;

import com.cubk.event.annotations.EventTarget;
import dev.sky.events.impls.player.EventUpdatePlayer;
import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.module.values.BoolValue;

/**
 * @author LangYa466
 * @date 2024/4/18 20:32
 */

public class Sprint extends Module {
    public Sprint( ) {
        super("Sprint", Category.Player);
        addSettings();
    }

    BoolValue testValue = new BoolValue("Test",false);

    @EventTarget
    void onU(EventUpdatePlayer e) {
        mc.gameSettings.keyBindSprint.setPressed(true);
    }
}
