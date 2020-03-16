package com.reliableplugins.antiskid.nms.impl;

import com.reliableplugins.antiskid.nms.INMSHandler;
import com.reliableplugins.antiskid.type.packet.*;
import com.reliableplugins.antiskid.type.packet.Packet;
import com.reliableplugins.antiskid.utils.Util;
import io.netty.channel.Channel;
import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Player;

import java.util.*;

public class Version_1_9_R1 implements INMSHandler
{
    @Override
    public Channel getSocketChannel(Player player)
    {
        return ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
    }

    @Override
    public void sendBlockChangePacket(Player player, Material material, Location location)
    {
        PacketPlayOutBlockChange packet =  new PacketPlayOutBlockChange(
                ((CraftWorld) location.getWorld()).getHandle(),
                new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));

        packet.block = CraftMagicNumbers.getBlock(material).getBlockData();

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @Override
    public void broadcastBlockChangePacket(Material material, Location location, TreeSet<UUID> whitelist)
    {
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        for(Player player : onlinePlayers)
        {
            if(whitelist != null && whitelist.contains(player.getUniqueId())) continue;
            sendBlockChangePacket(player, material, location);
        }
    }

    @Override
    public Packet getPacket(Object packet, Player player)
    {
        try
        {
            if(packet instanceof PacketPlayOutBlockChange)
            {
                PacketPlayOutBlockChange blockChange = (PacketPlayOutBlockChange) packet;
                BlockPosition bpos;
                bpos = Util.getPrivateField("a", blockChange);

                return new PacketServerBlockChange(new Location(player.getWorld(), bpos.getX(), bpos.getY(), bpos.getZ()), CraftMagicNumbers.getMaterial(blockChange.block.getBlock()));
            }
            else if(packet instanceof PacketPlayOutMapChunk)
            {
                PacketPlayOutMapChunk mapChunk = (PacketPlayOutMapChunk) packet;
                try
                {
                    int x = Util.getPrivateField("a", mapChunk);
                    int z = Util.getPrivateField("b", mapChunk);
                    return new PacketServerMapChunk(player.getWorld().getChunkAt(x, z));
                }
                catch(Exception e)
                {
                    return null;
                }
            }
            else if(packet instanceof PacketPlayInBlockDig)
            {
                PacketPlayInBlockDig pack = (PacketPlayInBlockDig) packet;
                BlockPosition bpos = pack.a();
                return new PacketClientLeftClickBlock(new Location(player.getWorld(), bpos.getX(), bpos.getY(), bpos.getZ()));
            }
            else if(packet instanceof PacketPlayOutExplosion)
            {
                PacketPlayOutExplosion pack = (PacketPlayOutExplosion) packet;
                List<BlockPosition> bposes = Util.getPrivateField("e", pack);
                Set<Location> positions = new HashSet<>();
                for(BlockPosition bpos : bposes)
                {
                    positions.add(new Location(player.getWorld(), bpos.getX(), bpos.getY(), bpos.getZ()));
                }
                return new PacketServerExplosion(positions);
            }
        }
        catch(Exception e)
        {
            return null;
        }

        return null;
    }
}
