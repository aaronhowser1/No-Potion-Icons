package dev.aaronhowser.mods.nopotionicons;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NoPotionIcons.MODID)
public class NoPotionIcons {
    public static final String MODID = "nopotionicons";

    public NoPotionIcons() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(
            modid = MODID,
            bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT
    )
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onRenderGuiEvent(RenderGuiOverlayEvent.Pre event) {
            if (event.getOverlay() == GuiOverlayManager.findOverlay(VanillaGuiOverlay.POTION_ICONS.id())) {
                event.setCanceled(true);
            }
        }

    }
}
