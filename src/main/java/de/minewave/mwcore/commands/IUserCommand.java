package de.minewave.mwcore.commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import de.minewave.mwcore.commands.internal.ICommand;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public interface IUserCommand extends ICommand {

	void info(CommandSender sender, List<String> arguments);
	
	void debug(CommandSender sender, List<String> arguments);
	
	void setGroup(CommandSender sender, List<String> arguments);
	
}
