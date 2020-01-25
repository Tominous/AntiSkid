/*
 * Project: AntiSkid
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.antiskid.nms;

import com.reliableplugins.antiskid.type.Vector;
import com.reliableplugins.antiskid.type.packet.BlockChangePacket;
import com.reliableplugins.antiskid.type.packet.MapChunkBulkPacket;
import io.netty.channel.Channel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.TreeSet;
import java.util.UUID;

public interface INMSHandler
{
    String getVersion();

    Channel getSocketChannel(Player player);

    void sendBlockChangePacket(Player player, Material material, Location location);

    void broadcastBlockChangePacket(Material material, Location location, TreeSet<UUID> whitelist);

    boolean isDiodeBlockChangePacket(Object packet);

    boolean isBlockChangePacket(Object packet);

    BlockChangePacket getBlockChangePacket(Object packet);

    MapChunkBulkPacket getMapChunkBulkPacket(Object packet);

    boolean isMapChunkBulkPacket(Object packet);

    Vector getLocation(Object packet);

    int[][] getChunkCoordinates(Object packet);
}