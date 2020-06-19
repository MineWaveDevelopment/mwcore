package de.minewave.mwcore.chat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;


/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ChatUtil {
	public static List<AdvancedChatMessage> messages = Lists.newArrayList();
	
	public static void sendReplyableMessage(Player sender, Collection<? extends Player> receivers, String message) {
		String identifier = getAlphaNumericString(6);
		AdvancedChatMessage advancedChatMessage = AdvancedChatMessage.of(identifier, sender.getUniqueId(), System.currentTimeMillis(), message);
		ChatUtil.messages.add(advancedChatMessage);
	
		User user = MwCorePlugin.getInstance().getUserManager().getUser(sender);
		
		TextComponent chatMessage = new TextComponent();
	
		TextComponent groupPrefixMessage = new TextComponent();
		String groupPrefix = ChatColor.translateAlternateColorCodes('&', user.getGroup().getGroupPrefix());
		
		groupPrefixMessage.setText(groupPrefix + " §r");
		groupPrefixMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§7Group §d" + user.getGroup().getName()).create()));
		
		TextComponent playerMessage = new TextComponent();
		
		playerMessage.setText(sender.getName() + " §7>> ");
	
		TextComponent modifiedMessage = new TextComponent();
		modifiedMessage.setText(ChatColor.translateAlternateColorCodes('&', message));
		modifiedMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§7Click to reply to §a" + sender.getName() + "'s §7message §8(" + identifier + ")").create()));
		modifiedMessage.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, "/chat r " + identifier + " "));
	
		chatMessage.addExtra(groupPrefixMessage);
		chatMessage.addExtra(playerMessage);
		chatMessage.addExtra(modifiedMessage);
	
		receivers.forEach(p -> p.spigot().sendMessage(chatMessage));
	
	}
	
	public static void sendQuotedMessage(Collection<? extends Player> receivers, AdvancedChatMessage advancedChatMessage) {
		Player sender = Bukkit.getPlayer(advancedChatMessage.getSender());
		
		TextComponent quotedMessage = new TextComponent();
		
		String text = "§7§o'" + sender.getName() + ": " + ChatColor.translateAlternateColorCodes('&', advancedChatMessage.getMessage()) + "'";
		quotedMessage.setText(text);
		
		LocalDateTime localDateTime =  Instant.ofEpochMilli(advancedChatMessage.getTimestamp()).atZone(ZoneId.systemDefault()).toLocalDateTime();
		String dateTimeFormatted = DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm:ss").format(localDateTime);
		
		quotedMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§7This message has been sent by §a" + sender.getName() + " §7at §e" + dateTimeFormatted).create()));
		
		receivers.forEach(p -> p.spigot().sendMessage(quotedMessage));
		
	}
	
	private static String getAlphaNumericString(int n) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
	
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
	
		return sb.toString();
	}
}

