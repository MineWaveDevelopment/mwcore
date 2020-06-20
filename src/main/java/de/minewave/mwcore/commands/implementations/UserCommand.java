package de.minewave.mwcore.commands.implementations;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MWAccess;
import de.minewave.mwcore.actions.internal.AsyncActions;
import de.minewave.mwcore.commands.IUserCommand;
import de.minewave.mwcore.commands.internal.KeyWords;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.user.User;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class UserCommand implements IUserCommand {

	@SubCommand(subCommandName = "info", subCommandSyntax = {})
	@Override
	public void info(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		AsyncActions.userInfoAction(player);
	}

	@SubCommand(subCommandName = "debug", subCommandSyntax = {"bool"})
	@Override
	public void debug(CommandSender sender, List<String> arguments) { // Action
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		boolean toggle = (boolean) KeyWords.getValue("bool", arguments.get(0));
		user.setDebug(toggle);
		
		user.message("§6Debug function has been set " + (toggle ? "§atrue" : "§cfalse"));
	}

	@SubCommand(subCommandName = "setGroup", subCommandSyntax = {"user", "group"})
	@Override
	public void setGroup(CommandSender sender, List<String> arguments) { // Action
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MWAccess.getUserManager().getUser(player);
		
		User userModified = (User) KeyWords.getValue("user", arguments.get(0));
		Group group = (Group) KeyWords.getValue("group", arguments.get(1));
		
		userModified.setGroupName(group.getName());
		user.message("§6Set group of user §a" + userModified.getName() + " §6to §d" + group.getName());
	}

}
