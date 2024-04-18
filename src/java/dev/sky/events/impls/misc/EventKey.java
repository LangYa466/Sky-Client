package dev.sky.events.impls.misc;

import com.cubk.event.impl.Event;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa466
 * @date 2024/4/14 13:18
 */


@Getter
@Setter
public class EventKey implements Event {
    int keyCode;
    public EventKey(int keyCode) {
        this.keyCode = keyCode;
    }
}
