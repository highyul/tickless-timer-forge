package io.github.highyul.ticklesstimer;

import com.mojang.logging.LogUtils;
import io.github.highyul.ticklesstimer.timer.RealTimer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;


@Mod(TicklessTimer.MODID)
public class TicklessTimer {

    public static final String MODID = "ticklesstimer";

    public static final RealTimer REAL_TIMER = new RealTimer();

    private static final Logger LOGGER = LogUtils.getLogger();

    private boolean timerInitialized = false;


    public TicklessTimer() {
        MinecraftForge.EVENT_BUS.register(this);

    }


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.player.level().isClientSide()) {
            return;
        }

        if (timerInitialized) {
            return;
        }

        REAL_TIMER.start();
        timerInitialized = true;

        LOGGER.info("Timer started");
    }


    @SubscribeEvent
    public void onLevelUnload(LevelEvent.Unload event) {
        if (event.getLevel().isClientSide()) return;

        REAL_TIMER.reset();
        timerInitialized = false;
    }


}
