package me.earth.earthhack.impl.modules.misc.rpc;

import me.earth.earthhack.api.module.Module;
import me.earth.earthhack.api.module.util.Category;
import me.earth.earthhack.api.setting.Setting;
import me.earth.earthhack.api.setting.settings.BooleanSetting;
import me.earth.earthhack.api.setting.settings.StringSetting;
import me.earth.earthhack.impl.util.discord.DiscordPresence;

public class RPC extends Module
{
    public static RPC INSTANCE;
    public final Setting<String> state =
            register(new StringSetting("State", "agalarhack"));
    public final Setting<String> details =
            register(new StringSetting("Details", "agalarhack"));
    public final Setting<Boolean> customDetails =
            register(new BooleanSetting("CustomDetails", false));
    public final Setting<Boolean> showIP =
            register(new BooleanSetting("ShowIP", false));
    public final Setting<Boolean> lokummode =
            register(new BooleanSetting("lokummode", false));

    public RPC()
    {
        super("RPC", Category.Misc);
        INSTANCE = this;
    }

    protected void onEnable()
    {
        DiscordPresence.start();
    }

    protected void onDisable()
    {
        DiscordPresence.stop();
    }

}
