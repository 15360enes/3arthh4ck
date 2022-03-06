package me.earth.earthhack.impl.modules.movement.speed2;

import me.earth.earthhack.api.module.data.DefaultData;

public final class SpeedData extends DefaultData<Speed2>
{
    public SpeedData(Speed2 module)
    {
        super(module);
        register(module.mode, "-Instant always move at 20.5 km/h." +
                "\n-OldGround old OnGroundSpeed." +
                "\n-OnGround move quickly on flat surfaces." +
                "\nVanilla move quickly into all directions as" +
                " specified by the Speed setting.");
        register(module.inWater, "Move quickly while in water.");
        register(module.speedSet, "Speed for Mode-Vanilla.");
    }

    @Override
    public int getColor()
    {
        return 0xffffffff;
    }

    @Override
    public String getDescription()
    {
        return "for people too lazy to make macros like me.";
    }

}
