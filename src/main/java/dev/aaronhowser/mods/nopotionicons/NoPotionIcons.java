package dev.aaronhowser.mods.nopotionicons;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(NoPotionIcons.MODID)
public class NoPotionIcons {

    static final String MODID = "nopotionicons";

    public NoPotionIcons() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(
            modid = MODID,
            bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public void onRenderGuiEvent(RenderGameOverlayEvent.PreLayer event) {
            if (event.getOverlay() == ForgeIngameGui.POTION_ICONS_ELEMENT) {
                event.setCanceled(true);
            }
        }
    }

}
