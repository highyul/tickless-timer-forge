package io.github.highyul.ticklesstimer.display;


import io.github.highyul.ticklesstimer.TicklessTimer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = TicklessTimer.MODID,
        value = Dist.CLIENT
)
public class TimerOverlay {


    public static String format(long millis) {

        long totalSeconds = millis / 1000;

        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        long centiseconds = (millis % 1000) / 10;

        if (hours > 0) {
            return String.format(
                    "%d:%02d:%02d.%02d",
                    hours,
                    minutes,
                    seconds,
                    centiseconds
            );
        }

        return String.format(
                "%d:%02d.%02d",
                minutes,
                seconds,
                centiseconds
        );
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        GuiGraphics gui = event.getGuiGraphics();

        String text = format(
                TicklessTimer.REAL_TIMER.elapsed()
        );

        int screenWidth = mc.getWindow().getGuiScaledWidth();

        int textWidth = mc.font.width(text);

        int x = screenWidth - textWidth - 5;
        int y = 5;

        gui.drawString(
                mc.font,
                text,
                x,
                y,
                0xFFFFFF
        );
    }
}