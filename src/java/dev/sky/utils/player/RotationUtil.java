package dev.sky.utils.player;

import dev.sky.utils.IMinecraft;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LangYa466
 * @date 2024/4/14 17:20
 */

@Getter
@Setter
public class RotationUtil extends IMinecraft {
    float yaw,pitch;

    public RotationUtil(float yaw,float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void visual() {
        mc.thePlayer.prevRenderYawOffset = yaw;
        mc.thePlayer.renderYawOffset = yaw;
        mc.thePlayer.prevRotationYawHead = yaw;
        mc.thePlayer.rotationYawHead = yaw;
        mc.thePlayer.rotationPitchHead = yaw;
    }


}
