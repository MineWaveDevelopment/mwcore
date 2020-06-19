package de.minewave.mwcore.commands.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.util.CommandHelper;
import de.minewave.mwcore.util.ConsoleHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class MineWaveCommandExecuter implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		boolean exists = CommandHelper.exists(label);
		
		if(!exists) {
			ConsoleHelper.message(sender, "§6Unknown command §b[" + label + "]");
			return true;
		}
		
		if(args.length == 0) {
			ConsoleHelper.message(sender, "§3ToDo: §6proivde command help");
			return true;
		}
		
		String subCommandName = args[0];
		List<String> arguments = Arrays.stream(args).skip(1).collect(Collectors.toList());
		
		ICommand com = CommandHelper.get(label);
		
		if(!CommandHelper.hasSubCommand(com, subCommandName)) {
			ConsoleHelper.unknownSubCommand(sender, label, subCommandName);
			return true;
		}
		
		SubCommand subCommand = CommandHelper.getSubCommand(com, subCommandName);
		
		if(arguments.size() < subCommand.subCommandSyntax().length) {
			ConsoleHelper.missingArguments(sender, label, subCommandName);
			return true;
		}
		
		if(arguments.size() > subCommand.subCommandSyntax().length) {
			ConsoleHelper.argumentsNotRequired(sender, label, subCommandName);
		}
		
		if((sender instanceof Player) && MwCorePlugin.getInstance().getUserManager().getUser((Player) sender).isDebug()) {
			ConsoleHelper.message(sender, "§3Permission information for this command");
			String permission = "mwcore." + label + "." + subCommand.subCommandName();
			ConsoleHelper.copyableMessage(sender, "§7[" + permission + "]", permission);
		}
		
		try {
			CommandHelper.getSubCommandMethod(com, subCommandName).invoke(com, sender, arguments);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
			
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

		String subCommandName = args[0];

		ICommand com = CommandHelper.get(label);
		if(com == null) return null;
		
		List<SubCommand> subCommands = CommandHelper.getSubCommands(com);
		
		if(args.length == 1) {
			return subCommands.stream().map(SubCommand::subCommandName).collect(Collectors.toList());
		}
		
		boolean exists = CommandHelper.hasSubCommand(com, subCommandName);
		if(!exists) return Lists.newArrayList("§8[§6Unknown argument§8]");
		
		SubCommand subCommand = CommandHelper.getSubCommand(com, subCommandName);
		if(subCommand == null) return Lists.newArrayList("§8[§6Unknown argument§8]");
		
		if((args.length - 2) >= subCommand.subCommandSyntax().length) return Lists.newArrayList("§8[§6No more arguments required§8]");
		
		if(args.length > 1) {
			return ArgumentType.getValues(subCommand.subCommandSyntax()[args.length - 2]);
		}

		return null;
	}

}
