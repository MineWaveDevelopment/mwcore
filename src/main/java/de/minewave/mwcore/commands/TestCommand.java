package de.minewave.mwcore.commands;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.minewave.mwcore.MwCorePlugin;
import de.minewave.mwcore.commands.internal.ArgumentType;
import de.minewave.mwcore.commands.internal.SubCommand;
import de.minewave.mwcore.user.User;
import de.minewave.mwcore.util.ConsoleHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2020 by FLXnet
 * @author Felix
 */
public class TestCommand implements ITestCommand {

	@SubCommand(subCommandName = "test", subCommandSyntax = {"decimal", "number", "group", "user"})
	@Override
	public void test(CommandSender sender, List<String> arguments) {
		ConsoleHelper.message(sender, "§2Wir testen was §b@" + sender.getName() + "§2!");
	}
	
	@SubCommand(subCommandName = "testSound", subCommandSyntax = {"sound"})
	@Override
	public void testSound(CommandSender sender, List<String> arguments) {
		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;
		User user = MwCorePlugin.getInstance().getUserManager().getUser(player);
		
		Sound sound = (Sound) ArgumentType.getValue("sound", arguments.get(0));
		player.playSound(player.getLocation(), sound, 1, 1);
		user.message("§2Playing sound §b" + sound);
	}
	
}
