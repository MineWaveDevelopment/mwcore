package de.minewave.mwcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.manager.UserManager;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		player.setPlayerListHeaderFooter("§6§lMine§c§lWave§2.de", "§aHallo! §b@" + player.getName());
		
		UserManager userManager = MwCorePlugin.getInstance().getUserManager();
		
		boolean exists = userManager.hasUser(player.getUniqueId());
		if(exists) {
			User user = userManager.getUser(player);
			user.setLastJoined(System.currentTimeMillis());
			ConsoleHelper.player(player, "§aWelcome back, §b@" + player.getName() + "§a!");
			return;
		}
		
		User user = User.create(player, "default");
		boolean added = userManager.addUser(user);
		
		if(!added) {
			ConsoleHelper.player(player, "§cYou server profile could not be created!");
			return;
		}
		
		ConsoleHelper.player(player, "§aYou server profile has been created!");
	}
	
}
