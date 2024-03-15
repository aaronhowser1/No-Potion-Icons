package dev.aaronhowser.nopotionicons;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("nopotionicons")
public class NoPotionIcons {
    public NoPotionIcons() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onRenderGuiEvent(RenderGameOverlayEvent.PreLayer event) {
        if (event.getOverlay() == ForgeIngameGui.POTION_ICONS_ELEMENT) {
            event.setCanceled(true);
        }
    }

}
