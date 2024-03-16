package dev.aaronhowser.mods.nopotionicons;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod(NoPotionIcons.MODID)
public class NoPotionIcons {

    static final String MODID = "nopotionicons";
    static KeyMapping TOGGLE_POTION_ICONS;
    public static boolean hidePotionIcons = true;


    public NoPotionIcons() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Mod.EventBusSubscriber(
            modid = MODID,
            bus = Mod.EventBusSubscriber.Bus.MOD,
            value = Dist.CLIENT
    )
    public static class ClientModBusEvents {


        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            TOGGLE_POTION_ICONS = new KeyMapping(
                    "key.nopotionicons.toggle_potion_icons",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_COMMA,
                    "key.categories.ui"
            );
            ClientRegistry.registerKeyBinding(TOGGLE_POTION_ICONS);
        }


        @SubscribeEvent
        public void onRenderGuiEvent(RenderGameOverlayEvent.PreLayer event) {
            if (!hidePotionIcons) return;

            if (event.getOverlay() == ForgeIngameGui.POTION_ICONS_ELEMENT) {
                event.setCanceled(true);
            }
        }

    }

    @Mod.EventBusSubscriber(
            modid = MODID,
            value = Dist.CLIENT
    )
    public static class ClientEvents {
        @SubscribeEvent
        public void onKeyInputEvent(InputEvent.KeyInputEvent event) {
            if (TOGGLE_POTION_ICONS.consumeClick()) {
                System.out.println("Toggled potion icons! Now " + (hidePotionIcons ? "hidden" : "visible") + "!");
                hidePotionIcons = !hidePotionIcons;
            }
        }
    }

}
