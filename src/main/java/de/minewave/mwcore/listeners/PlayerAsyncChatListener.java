package de.minewave.mwcore.listeners;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.google.common.collect.Lists;

import de.minewave.mwcore.chat.ChatMentionedPlayer;
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
		
		StringBuilder builder = new StringBuilder(message);
		List<ChatMentionedPlayer> mentioned = Lists.newArrayList();
		
		for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			
			String name = onlinePlayer.getName();
			String nameLowercase = name.toLowerCase();
			
			if(message.toLowerCase().contains(nameLowercase)) {
				ChatHelper.notifySound(onlinePlayer, Sound.BLOCK_NOTE_BLOCK_BELL);
				int index = message.toLowerCase().indexOf(nameLowercase);
				index = index == 0 ? 0 : index;
				mentioned.add(new ChatMentionedPlayer(index, name));
			}
			
		}

		mentioned = mentioned.stream().sorted().collect(Collectors.toList());
		for(ChatMentionedPlayer chatMentionedPlayer : mentioned) {
			builder = builder.replace(chatMentionedPlayer.getStartIndex(), chatMentionedPlayer.getStartIndex() + chatMentionedPlayer.getName().length(), "§b" + chatMentionedPlayer.getName() + "§7");
		}
		
		ChatHelper.sendAdvancedChatMessage(player, Bukkit.getOnlinePlayers(), builder.toString());
	}
	
}
