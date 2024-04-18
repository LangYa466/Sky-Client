package dev.sky.module.impls.display;

import com.cubk.event.annotations.EventTarget;
import dev.sky.events.impls.render.EventRender2D;
import dev.sky.module.Category;
import dev.sky.module.Module;

public class HUDMod extends Module {
    public HUDMod() {
        super("HUDMod","customizes the client's appearance", Category.Render);
    }

    @EventTarget
    public void onRender2DEvent(EventRender2D e){ }
}
