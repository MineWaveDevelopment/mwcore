package de.minewave.mwcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.user.User;
import net.md_5.bungee.api.ChatColor;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PlayerLeaveListener implements Listener {

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		
		e.setQuitMessage("§7[§c-§7] " + ChatColor.translateAlternateColorCodes('&', user.getGroup().getGroupPrefix()) + " §r" + user.getName());
		
	}
	
}
