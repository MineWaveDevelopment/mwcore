package de.minewave.mwcore.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
public class ConsoleHelper {

	public static String prefix = "§f[§6Mine§cWave§f] §r";
	
	public static void player(Player player, String message) {
		player.sendMessage(prefix + message);
	}
	
	public static void copyableMessage(CommandSender sender, String message, String copyable) {
		TextComponent copyableMessage = new TextComponent();
		copyableMessage.setText(prefix + message);
		copyableMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§7Click to copy permission node to the command line").create()));
		copyableMessage.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.SUGGEST_COMMAND, copyable));
		((Player) sender).spigot().sendMessage(copyableMessage);
	}
	
	public static void unknownSubCommand(CommandSender sender, String commandName, String givenSubCommand) {
		message(sender, "§6Unknown argument §b[" + givenSubCommand + "] §6for command §b[" + commandName + "]");
	}
	
	public static void missingArguments(CommandSender sender, String commandName, String givenSubCommand) {
		if(!(sender instanceof Player)) {
			message(sender, "§6There are arguments missing for §b[" + commandName + "] §7[" + givenSubCommand + "]");
			return;
		}
		
		TextComponent message = new TextComponent();
		message.setText(prefix + "§6There are arguments missing for ");
		
		TextComponent commandMessage = new TextComponent();
		commandMessage.setText("§b[" + commandName + "] ");
		commandMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§2Your given command").create()));
		
		TextComponent subCommandMessage = new TextComponent();
		subCommandMessage.setText("§7[" + givenSubCommand + "]");
		subCommandMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§2Your given sub-command/argument").create()));
		
		message.addExtra(commandMessage);
		message.addExtra(subCommandMessage);
		
		((Player) sender).spigot().sendMessage(message);
	}
	
	public static void argumentsNotRequired(CommandSender sender, String commandName, String givenSubCommand) {
		if(!(sender instanceof Player)) {
			message(sender, "§6Provided not required arguments for §b[" + commandName + "] §7[" + givenSubCommand + "]");
			return;
		}
		
		TextComponent message = new TextComponent();
		message.setText(prefix + "§6Provided not required arguments for ");
		
		TextComponent commandMessage = new TextComponent();
		commandMessage.setText("§b[" + commandName + "] ");
		commandMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§2Your given command").create()));
		
		TextComponent subCommandMessage = new TextComponent();
		subCommandMessage.setText("§7[" + givenSubCommand + "]");
		subCommandMessage.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ComponentBuilder("§2Your given sub-command/argument").create()));
		
		message.addExtra(commandMessage);
		message.addExtra(subCommandMessage);
		
		((Player) sender).spigot().sendMessage(message);
	}
	
	/**
	 * 
	 * @param sender
	 * @param message
	 */
	public static void message(CommandSender sender, String message) {
		sender.sendMessage(prefix + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerSuccess(Player player, String message) {
		player(player, "§a" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerWarning(Player player, String message) {
		player(player, "§6" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerError(Player player, String message) {
		player(player, "§4Error: §c" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerInfo(Player player, String message) {
		player(player, "§3" + message);
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void playerNoPermission(Player player) {
		player(player, "§cYou do not have the permission to use this command.");
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void console(String message) {
		Bukkit.getConsoleSender().sendMessage(prefix + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleSuccess(String message) {
		console("§a" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleWarning(String message) {
		console("§6" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleError(String message) {
		console("§4Error: §c" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleInfo(String message) {
		console("§3" + message);
	}
	
}
