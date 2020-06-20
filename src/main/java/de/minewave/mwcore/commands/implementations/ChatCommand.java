package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.commands.IChatCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.user.User;

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

}
