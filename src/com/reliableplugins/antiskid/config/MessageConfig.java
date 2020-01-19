/*
 * Project: AntiSkid
 * Copyright (C) 2020 Bilal Salha <bsalha1@gmail.com>
 * GNU GPLv3 <https://www.gnu.org/licenses/gpl-3.0.en.html>
 */

package com.reliableplugins.antiskid.config;

import com.reliableplugins.antiskid.AntiSkid;
import com.reliableplugins.antiskid.abstracts.AbstractConfig;

public class MessageConfig extends AbstractConfig
{
    public MessageConfig(AntiSkid plugin, String fileName)
    {
        super(plugin, fileName);
        addDefault("message-header", "&8&l(&9&lAntiSkid&8&l) ");
        addDefault("antiskid-on", "&bAntiskid protection has been turned on ({NUM} repeaters protected)");
        addDefault("antiskid-off", "&bAntiskid protection has been turned off.");
        addDefault("antiskid-reload-start", "&bAntiskid is reloading.");
        addDefault("antiskid-reload-finish", "&bAntiskid has reloaded.");

        addDefault("whitelist-add", "&7{PLAYER} added to whitelist.");
        addDefault("whitelist-remove", "&7{PLAYER} removed from whitelist.");
        addDefault("whitelist-list", "&7Whitelist: {LIST}");

        addDefault("antiskid-help", "&c/antiskid <whitelist/on/off> <add/del> <player>");
        addDefault("antiskid-whitelist-help", "&c/antiskid whitelist <add/del> <player>");

        addDefault("err-no-perms", "&cYou do not have access to this command!");
        addDefault("err-not-player", "&cOnly players may execute this command.");
        addDefault("err-protected-diode", "&cThat repeater is protected!");
        addDefault("err-not-protected", "&cYou have not turned on antiskid yet.");
        addDefault("err-empty-whitelist", "&cYou have no players in your whitelist.");
        addDefault("err-not-whitelisted", "&c{PLAYER} is not in your whitelist.");
        addDefault("err-already-whitelisted", "&c{PLAYER} is already whitelisted.");
        addDefault("err-invalid-player", "&cInvalid player.");
        addDefault("err-whitelist-self", "&cYou cannot whitelist yourself.");
        addDefault("err-whitelist-remove-self", "&cYou are not in your whitelist.");
        addDefault("err-not-territory", "&cYou can only protect your faction's claims.");

        loadDefaults();
    }
}
