package dev.sky.manager;

import com.cubk.event.annotations.EventTarget;
import dev.sky.Client;
import dev.sky.events.impls.player.EventAttack;
import dev.sky.events.impls.player.EventUpdatePlayer;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.Entity;

/**
 * @author LangYa466
 * @date 2024/4/17 20:43
 */

@Getter
@Setter
public class TargetManager {
    public static final TargetManager INSTANCE = new TargetManager();

    Entity target;

    public TargetManager() {
        Client.INSTANCE.getEventManager().register(this);
    }

    @EventTarget
    void onA(EventAttack e) {
        target = e.getEntity();
    }

    @EventTarget
    void onU(EventUpdatePlayer e) {
        if (target == null) return;
        if(target.isDead) target = null;
    }
}
