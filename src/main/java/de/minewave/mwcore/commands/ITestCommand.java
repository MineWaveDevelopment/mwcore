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
public interface ITestCommand extends ICommand {

	void test(CommandSender sender, List<String> arguments);
	
	void testSound(CommandSender sender, List<String> arguments);
	
}
