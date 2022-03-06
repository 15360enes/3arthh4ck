package me.earth.earthhack.impl.util.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.earth.earthhack.api.cache.ModuleCache;
import me.earth.earthhack.impl.Duny4hil3;
import me.earth.earthhack.impl.modules.Caches;
import me.earth.earthhack.impl.modules.misc.rpc.RPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

public class DiscordPresence {

    private static final ModuleCache<RPC> RPC =
            Caches.getModule(RPC.class);

    public static DiscordRichPresence presence = new DiscordRichPresence();
    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;
    private static Thread thread;

    private static int index = 1;

    public static void start() {
        if (RPC.isEnabled()) {
            final DiscordEventHandlers handlers = new DiscordEventHandlers();
            rpc.Discord_Initialize("910141516356411412", handlers, true, "");
            presence.startTimestamp = System.currentTimeMillis() / 1000L;
            presence.details =
                    RPC.get().customDetails.getValue() ? RPC.get().details.getValue() :
                        Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu
                            ? "In the main menu."
                            : "Playing " + (Minecraft.getMinecraft().getCurrentServerData() != null
                                ? (RPC.get().showIP.getValue() ? "on " + Minecraft.getMinecraft().getCurrentServerData().serverIP + "."
                                : " multiplayer.") : " singleplayer.");
            presence.state = RPC.get().state.getValue();
            presence.largeImageKey = "agalar";
            presence.largeImageText = Duny4hil3.NAME + " " + Duny4hil3.VERSION;
            rpc.Discord_UpdatePresence(DiscordPresence.presence);
            thread = new Thread(() ->
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    rpc.Discord_RunCallbacks();
                    presence.details =
                            RPC.get().customDetails.getValue() ? RPC.get().details.getValue() :
                                    Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu
                                            ? "In the main menu."
                                            : "Playing " + (Minecraft.getMinecraft().getCurrentServerData() != null
                                                ? (RPC.get().showIP.getValue() ? "on " + Minecraft.getMinecraft().getCurrentServerData().serverIP + "."
                                                : " multiplayer.") : " singleplayer.");
                    presence.state = RPC.get().state.getValue();
                    if (RPC.get().lokummode.getValue())
                    {
                        if (index == 8) index = 1;
                        presence.largeImageKey = "lokum" + index;
                        index++;
                    }
                    rpc.Discord_UpdatePresence(presence);
                    try
                    {
                        Thread.sleep(4000);
                    }
                    catch (InterruptedException ignored) {}
                }
            }, "RPC-Callback-Handler");
            thread.start();
        }
    }


    public static void stop()
    {
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }


}
