package dev.sky.module.impls.render;

import com.cubk.event.annotations.EventTarget;
import dev.sky.events.impls.network.EventPacketReceive;
import dev.sky.events.impls.player.EventUpdatePlayer;
import dev.sky.module.Category;
import dev.sky.module.Module;
import dev.sky.module.values.FloatValue;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

/**
 * @author LangYa466
 * @date 2024/4/22 18:03
 */

public class WorldTimeChanger extends Module {

    // 默认参数晚上
    FloatValue timeValue = new FloatValue("世界时间",14000,24000,0);

    public WorldTimeChanger() {
        super("世界时间修改", Category.Render);
    }

    @EventTarget
    void onUp(EventUpdatePlayer e) {
        if (mc.theWorld != null) mc.theWorld.setWorldTime(timeValue.get().longValue());
    }

    @EventTarget
    void onPr(EventPacketReceive e) {
        if(e.getPacket() instanceof S03PacketTimeUpdate) e.setCancelled(true);
    }

}
