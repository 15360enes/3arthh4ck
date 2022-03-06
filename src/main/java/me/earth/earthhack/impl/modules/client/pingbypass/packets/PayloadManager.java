package me.earth.earthhack.impl.modules.client.pingbypass.packets;

import me.earth.earthhack.impl.Duny4hil3;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPacketCustomPayload;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayloadManager
{
    private final Map<Short, PayloadReader> readers = new ConcurrentHashMap<>();

    public void onPacket(SPacketCustomPayload packet)
    {
        PacketBuffer buffer = packet.getBufferData();

        short id;
        try
        {
            id = buffer.readShort();
        }
        catch (Exception e)
        {
            Duny4hil3.getLogger().error("Could not read id from PayloadPacket.");
            return;
        }

        PayloadReader reader = readers.get(id);
        if (reader == null)
        {
            Duny4hil3.getLogger().error("Couldn't find PayloadReader for ID: " + id);
            return;
        }

        try
        {
            reader.read(buffer);
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    public void register(short id, PayloadReader reader)
    {
        readers.compute(id, (i, v) -> v == null ? reader : v.compose(reader));
    }

}
