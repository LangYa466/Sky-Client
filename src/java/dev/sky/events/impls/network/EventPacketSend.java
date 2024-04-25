package dev.sky.events.impls.network;

import com.cubk.event.impl.CancellableEvent;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.Packet;

/**
 * @author LangYa466
 * @date 2024/4/22 18:05
 */

@Getter
@Setter
public class EventPacketSend extends CancellableEvent {

    Packet packet;

    public EventPacketSend(Packet packet) {
        this.packet = packet;
    }

}
