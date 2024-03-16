package dev.aaronhowser.mods.nopotionicons;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod(NoPotionIcons.MODID)
public class NoPotionIcons {
    public static final String MODID = "nopotionicons";

    public NoPotionIcons() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final KeyMapping TOGGLE_POTION_ICONS = new KeyMapping(
            "key.nopotionicons.toggle_potion_icons",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            "key.categories.ui"
    );

    @Mod.EventBusSubscriber(
            modid = MODID,
            value = Dist.CLIENT
    )
    public static class ClientModEvents {

        public static boolean hidePotionIcons = true;

        @SubscribeEvent
        public static void onRenderGuiEvent(RenderGuiOverlayEvent.Pre event) {
            if (!hidePotionIcons) return;

            if (event.getOverlay() == GuiOverlayManager.findOverlay(VanillaGuiOverlay.POTION_ICONS.id())) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent
        public static void onKeyInputEvent(InputEvent.Key event) {
            if (TOGGLE_POTION_ICONS.consumeClick()) {
                hidePotionIcons = !hidePotionIcons;
            }
        }

    }

    @Mod.EventBusSubscriber(
            modid = MODID,
            value = Dist.CLIENT,
            bus = Mod.EventBusSubscriber.Bus.MOD
    )
    public static class ClientModBusEvents {


        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(TOGGLE_POTION_ICONS);
        }
    }
}
