package dev.sky.events.impls.render;

import com.cubk.event.impl.Event;
import dev.sky.events.EventState;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa466
 * @date 2024/4/10 12:40
 */

@Getter
@Setter
public class EventRender2D implements Event {
    float partialTicks;
    EventState eventState;

    public EventRender2D(float partialTicks, EventState eventState) {
        this.partialTicks = partialTicks;
        this.eventState = eventState;
    }

}
