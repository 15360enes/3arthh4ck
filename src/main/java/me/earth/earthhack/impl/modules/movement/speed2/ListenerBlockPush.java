package me.earth.earthhack.impl.modules.movement.speed2;

import me.earth.earthhack.impl.event.events.movement.BlockPushEvent;
import me.earth.earthhack.impl.event.listeners.ModuleListener;
import me.earth.earthhack.impl.modules.movement.speed2.Speed2;

public final class ListenerBlockPush extends ModuleListener<Speed2, BlockPushEvent>
{
    public ListenerBlockPush(Speed2 module)
    {
        super(module, BlockPushEvent.class, 999);
    }

    @Override
    public void invoke(BlockPushEvent event)
    {
        if (module.lagOut.getValue())
        {
            event.setCancelled(false);
        }
    }

}
