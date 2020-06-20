package de.minewave.mwcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.manager.UserManager;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;
import de.minewave.mwcore.util.PlayerHelper;
import net.md_5.bungee.api.ChatColor;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) { // Check if player name has changed
		Player player = e.getPlayer();
		
		UserManager userManager = MwCorePlugin.getInstance().getUserManager();
		
		boolean exists = userManager.hasUser(player.getUniqueId());
		if(exists) {
			User user = userManager.getUser(player);
			user.setLastJoined(System.currentTimeMillis());
			ConsoleHelper.player(player, "§aWelcome back, §b@" + player.getName() + "§a!");
		} else {
			User user = User.create(player, "default");
			boolean added = userManager.addUser(user);
			
			if(!added) {
				ConsoleHelper.player(player, "§cYou server profile could not be created!");
				return;
			}
			
			ConsoleHelper.player(player, "§aYou server profile has been created!");
		}
		
		User user = userManager.getUser(player);
		
		PlayerHelper.setPlayerListHeaderFooter(player);
		PlayerHelper.setPlayerListName(user);
		
		e.setJoinMessage("§7[§a+§7] " + ChatColor.translateAlternateColorCodes('&', user.getGroup().getGroupPrefix()) + " §r" + user.getName());

	}
	
}
