package de.minewave.mwcore.util;

import org.bukkit.entity.Player;

import de.minewave.mwcore.user.User;
import net.md_5.bungee.api.ChatColor;

/**
 * Software by FLXnet More info at FLXnet.de Copyright (c) 2015-2020 by FLXnet
 * 
 * @author Felix
 */
public class PlayerHelper {

	
	public static void setPlayerListName(User user) {
		user.getPlayer().setPlayerListName(ChatColor.translateAlternateColorCodes('&', user.getGroup().getGroupPrefix()) + " §r" + user.getName());
	}
	
	public static void setPlayerListHeaderFooter(Player player) {
		player.setPlayerListHeaderFooter("§6§lMine§c§lWave§2.de", "§aHallo! §b@" + player.getName());
	}
}
