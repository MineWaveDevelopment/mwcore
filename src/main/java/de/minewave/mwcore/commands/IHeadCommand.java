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
public interface IHeadCommand extends ICommand {

	void get(CommandSender sender,  List<String> arguments);
	
	void create(CommandSender sender, List<String> arguments);
	
	void list(CommandSender sender, List<String> arguments);
	
	void give(CommandSender sender, List<String> arguments);
	
	void setDisplayname(CommandSender sender, List<String> arguments);
	
}
