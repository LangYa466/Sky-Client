package dev.sky.events.impls.player;

import com.cubk.event.impl.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.Entity;

/**
 * @author LangYa466
 * @date 2024/4/18 22:03
 */

@Getter
@Setter
public class EventAttack implements Event {
    Entity entity;

    public EventAttack(Entity entity) {
        this.entity = entity;
    }
}
