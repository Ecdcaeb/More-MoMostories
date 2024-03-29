package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.fixBug;

import com.gq2529.momostories.events.RenderTooltipEventO;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@SideOnly(Side.CLIENT)
@Mixin(RenderTooltipEventO.class)
public class MixinRenderTooltipO {

    /**
     * @author Hileb
     * @reason set the tooltip adjustable
     */
    @Overwrite
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRenderTooltip(RenderTooltipEvent.Pre event) {}
}
