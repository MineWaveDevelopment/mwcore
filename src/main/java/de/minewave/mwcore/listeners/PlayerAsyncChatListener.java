package de.minewave.mwcore.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.minewave.mwcore.util.ChatHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class PlayerAsyncChatListener implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		String message = e.getMessage();
		
		e.setCancelled(true);
		
		ChatHelper.sendReplyableMessage(player, Bukkit.getOnlinePlayers(), message);
		ChatHelper.notifySound(Bukkit.getOnlinePlayers(), Sound.BLOCK_NOTE_BLOCK_BELL);
		
		if(message.contains("@")) {
			Bukkit.getOnlinePlayers().stream().forEach(onlinePlayer -> {
				if(message.contains("@" + onlinePlayer.getName())) {
					player.sendMessage("mentioned " + onlinePlayer.getName());
				}
			});
		}
		
	}
	
}
