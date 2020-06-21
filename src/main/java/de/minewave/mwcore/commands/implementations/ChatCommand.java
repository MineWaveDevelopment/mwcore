package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.commands.IChatCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ChatHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class ChatCommand implements IChatCommand {

	@SubCommand(subCommandName = "reply", subCommandSyntax = {"word", "text"})
	@Override
	public void reply(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = User.getPlayer(sender);
		User user = MWAccess.getUserManager().getUser(player);
		
		String chatId = (String) KeyWords.getValue("word", arguments.get(0));
		String message = KeyWords.getStringFromTextKeyWord(arguments, 1);
		
		user.message("§9Reply: §b" + chatId + " §7" + message);
	}

	@SubCommand(subCommandName = "p", subCommandSyntax = {"user", "text"})
	@Override
	public void p(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = User.getPlayer(sender);
		User user = MWAccess.getUserManager().getUser(player);
		
		User receiver = (User) KeyWords.getValue("user", arguments.get(0));
		String text = KeyWords.getStringFromTextKeyWord(arguments, 1);
		
		if(!receiver.isOnline()) {
			user.message("§6Sorry, but §7" + receiver.getName() + " §6is currently not online.");
			return;
		}
		
		ChatHelper.sendPrivateMessage(user, receiver, text);
		user.lightMessage("§8[§3P§8] §2" + receiver.getName() + " §e<- §7§o" + text);
	}

}
