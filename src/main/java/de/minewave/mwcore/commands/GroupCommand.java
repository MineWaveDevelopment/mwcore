
package de.minewave.mwcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.actions.internal.AsyncActions;
import de.minewave.mwcore.commands.internal.ArgumentType;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.group.Group;
import de.minewave.mwcore.user.User;
import net.md_5.bungee.api.ChatColor;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class GroupCommand implements IGroupCommand {

	@SubCommand(subCommandName = "list", subCommandSyntax = {})
	@Override
	public void list(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		AsyncActions.listGroupsAction(user);
	}

	@SubCommand(subCommandName = "create", subCommandSyntax = {"name"})
	@Override
	public void create(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		
		String name = (String) ArgumentType.getValue("name", arguments.get(0));
		Group group = new Group(name);
		group.getPermissions().add("mwcore.default");
		
		AsyncActions.createGroupAction(user, group);
	}

	@SubCommand(subCommandName = "delete", subCommandSyntax = {"group"})
	@Override
	public void delete(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		
		String name = (String) ArgumentType.getValue("name", arguments.get(0));
		AsyncActions.removeGroupActon(user, name);
	}

	@SubCommand(subCommandName = "info", subCommandSyntax = {"group"})
	@Override
	public void info(CommandSender sender, List<String> arguments) { // Action
		
	}

	@SubCommand(subCommandName = "setPrefix", subCommandSyntax = {"group", "coloredText"})
	@Override
	public void setPrefix(CommandSender sender, List<String> arguments) { // Action
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		
		Group group = (Group) ArgumentType.getValue("group", arguments.get(0));
		String prefix = (String) ArgumentType.getValue("coloredText", arguments.get(1));
		
		group.setGroupPrefix(prefix);
		user.message("§6Changed prefix of group §d" + group.getName() + " §6to " + ChatColor.translateAlternateColorCodes('&', prefix));
	}

}
