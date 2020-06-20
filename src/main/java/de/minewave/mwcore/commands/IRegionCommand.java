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
public interface IRegionCommand extends ICommand {

	void info(CommandSender sender, List<String> arguments);
	
	void buy(CommandSender sender, List<String> arguments);
	
	void list(CommandSender sender, List<String> arguments);
	
}
