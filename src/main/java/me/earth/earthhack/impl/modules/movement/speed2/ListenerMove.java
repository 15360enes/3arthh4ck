package me.earth.earthhack.impl.modules.movement.speed2;

import me.earth.earthhack.api.cache.ModuleCache;
import me.earth.earthhack.impl.event.events.movement.MoveEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.modules.Caches;
import me.earth.earthhack.impl.modules.movement.packetfly.PacketFly;
import me.earth.earthhack.impl.modules.player.freecam.Freecam;
import me.earth.earthhack.impl.modules.player.ncptweaks.NCPTweaks;
import me.earth.earthhack.impl.util.math.position.PositionUtil;

public final class ListenerMove extends ModuleListener<Speed2, MoveEvent>
{
    private static final ModuleCache<PacketFly> PACKET_FLY =
            Caches.getModule(PacketFly.class);
    private static final ModuleCache<Freecam> FREECAM =
            Caches.getModule(Freecam.class);
    private static final ModuleCache<NCPTweaks> NCP_TWEAKS =
            Caches.getModule(NCPTweaks.class);

    public ListenerMove(Speed2 module)
    {
        super(module, MoveEvent.class);
    }

    @Override
    public void invoke(MoveEvent event)
    {
        if (PACKET_FLY.isEnabled()
                || FREECAM.isEnabled()
                || NCP_TWEAKS.isEnabled() && NCP_TWEAKS.get().isSpeedStopped())
        {
            return;
        }

        if (!module.inWater.getValue()
                && (PositionUtil.inLiquid() || PositionUtil.inLiquid(true))
                || mc.player.isOnLadder()
                || mc.player.isEntityInsideOpaqueBlock())
        {
            module.stop = true;
            return;
        }

        if (module.stop)
        {
            module.stop = false;
            return;
        }

        module.mode.getValue().move(event, module);
    }

}
